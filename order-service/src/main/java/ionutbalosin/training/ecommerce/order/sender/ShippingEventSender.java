/**
 *  eCommerce Application
 *
 * Copyright (C) 2022-2025 Ionut Balosin
 * Website:      www.ionutbalosin.com
 * Social Media:
 *   LinkedIn:   ionutbalosin
 *   Bluesky:    @ionutbalosin.bsky.social
 *   X:          @ionutbalosin
 *   Mastodon:   ionutbalosin@mastodon.social
 *
 *  MIT License
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 *
 */
package ionutbalosin.training.ecommerce.order.sender;

import ionutbalosin.training.ecommerce.message.schema.shipping.ShippingTriggerCommand;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
public class ShippingEventSender {

  private static final Logger LOGGER = LoggerFactory.getLogger(ShippingEventSender.class);

  public static final String SHIPPING_IN_TOPIC = "ecommerce-shipping-in-topic";

  private final KafkaTemplate<String, ShippingTriggerCommand> kafkaTemplate;

  public ShippingEventSender(KafkaTemplate<String, ShippingTriggerCommand> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void send(ShippingTriggerCommand command) {
    final CompletableFuture<SendResult<String, ShippingTriggerCommand>> future =
        kafkaTemplate.send(SHIPPING_IN_TOPIC, command);
    future.whenComplete(
        (result, failure) -> {
          if (failure == null) {
            LOGGER.debug("Sent message '{}' to Kafka topic '{}'", command, SHIPPING_IN_TOPIC);
          } else {
            LOGGER.error(
                "Unable to send message '{}' to Kafka topic '{}', exception '{}'",
                command,
                SHIPPING_IN_TOPIC,
                failure);
          }
        });
  }
}
