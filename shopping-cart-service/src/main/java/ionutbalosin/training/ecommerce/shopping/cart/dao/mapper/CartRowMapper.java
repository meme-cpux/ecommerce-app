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
package ionutbalosin.training.ecommerce.shopping.cart.dao.mapper;

import static ionutbalosin.training.ecommerce.shopping.cart.util.DateUtil.timestampToLocalDateTime;

import ionutbalosin.training.ecommerce.shopping.cart.model.CartItem;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import org.springframework.jdbc.core.RowMapper;

public class CartRowMapper implements RowMapper<CartItem> {

  @Override
  public CartItem mapRow(ResultSet rs, int rowNum) throws SQLException {
    return new CartItem()
        .id(UUID.fromString(rs.getString(CartItem.ID)))
        .userId(UUID.fromString(rs.getString(CartItem.USER_ID)))
        .productId(UUID.fromString(rs.getString(CartItem.PRODUCT_ID)))
        .dateIns(timestampToLocalDateTime(rs.getTimestamp(CartItem.DAT_INS)))
        .usrIns(rs.getString(CartItem.USR_INS))
        .dateUpd(timestampToLocalDateTime(rs.getTimestamp(CartItem.DAT_UPD)))
        .usrUpd(rs.getString(CartItem.USR_UPD))
        .stat(rs.getString(CartItem.STAT));
  }
}
