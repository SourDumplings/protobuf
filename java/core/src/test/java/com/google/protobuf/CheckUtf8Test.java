// Protocol Buffers - Google's data interchange format
// Copyright 2008 Google Inc.  All rights reserved.
//
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file or at
// https://developers.google.com/open-source/licenses/bsd

package com.google.protobuf;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

import proto2_test_check_utf8.TestCheckUtf8.BytesWrapper;
import proto2_test_check_utf8.TestCheckUtf8.StringWrapper;
import proto2_test_check_utf8_size.TestCheckUtf8Size.BytesWrapperSize;
import proto2_test_check_utf8_size.TestCheckUtf8Size.StringWrapperSize;
import java.io.ByteArrayInputStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Test that protos generated with file option java_string_check_utf8 do in fact perform appropriate
 * UTF-8 checks.
 */
@RunWith(JUnit4.class)
public class CheckUtf8Test {

  private static final String UTF8_BYTE_STRING_TEXT = "some text π \uD83D\uDE00";
  private static final ByteString UTF8_BYTE_STRING = ByteString.copyFromUtf8(UTF8_BYTE_STRING_TEXT);
  private static final ByteString NON_UTF8_BYTE_STRING =
      ByteString.copyFrom(new byte[] {(byte) 0x80}); // A lone continuation byte.

  @Test
  public void testBuildRequiredStringWithGoodUtf8() throws Exception {
    assertThat(StringWrapper.newBuilder().setReqBytes(UTF8_BYTE_STRING).getReq())
        .isEqualTo(UTF8_BYTE_STRING_TEXT);
  }

  @Test
  public void testParseRequiredStringWithGoodUtf8() throws Exception {
    ByteString serialized =
        BytesWrapper.newBuilder().setReq(UTF8_BYTE_STRING).build().toByteString();
    assertThat(StringWrapper.parseFrom(serialized).getReq()).isEqualTo(UTF8_BYTE_STRING_TEXT);
  }

  @Test
  public void testBuildRequiredStringWithBadUtf8() throws Exception {
    try {
      StringWrapper.newBuilder().setReqBytes(NON_UTF8_BYTE_STRING);
      assertWithMessage("Expected IllegalArgumentException for non UTF-8 byte string.").fail();
    } catch (IllegalArgumentException exception) {
      assertThat(exception).hasMessageThat().isEqualTo("Byte string is not UTF-8.");
    }
  }

  @Test
  public void testBuildOptionalStringWithBadUtf8() throws Exception {
    try {
      StringWrapper.newBuilder().setOptBytes(NON_UTF8_BYTE_STRING);
      assertWithMessage("Expected IllegalArgumentException for non UTF-8 byte string.").fail();
    } catch (IllegalArgumentException exception) {
      assertThat(exception).hasMessageThat().isEqualTo("Byte string is not UTF-8.");
    }
  }

  @Test
  public void testBuildRepeatedStringWithBadUtf8() throws Exception {
    try {
      StringWrapper.newBuilder().addRepBytes(NON_UTF8_BYTE_STRING);
      assertWithMessage("Expected IllegalArgumentException for non UTF-8 byte string.").fail();
    } catch (IllegalArgumentException exception) {
      assertThat(exception).hasMessageThat().isEqualTo("Byte string is not UTF-8.");
    }
  }

  @Test
  public void testParseRequiredStringWithBadUtf8() throws Exception {
    byte[] serialized =
        BytesWrapper.newBuilder().setReq(NON_UTF8_BYTE_STRING).build().toByteArray();
    assertParseBadUtf8(StringWrapper.getDefaultInstance(), serialized);
  }

  @Test
  public void testBuildRequiredStringWithBadUtf8Size() throws Exception {
    try {
      StringWrapperSize.newBuilder().setReqBytes(NON_UTF8_BYTE_STRING);
      assertWithMessage("Expected IllegalArgumentException for non UTF-8 byte string.").fail();
    } catch (IllegalArgumentException exception) {
      assertThat(exception).hasMessageThat().isEqualTo("Byte string is not UTF-8.");
    }
  }

  @Test
  public void testBuildOptionalStringWithBadUtf8Size() throws Exception {
    try {
      StringWrapperSize.newBuilder().setOptBytes(NON_UTF8_BYTE_STRING);
      assertWithMessage("Expected IllegalArgumentException for non UTF-8 byte string.").fail();
    } catch (IllegalArgumentException exception) {
      assertThat(exception).hasMessageThat().isEqualTo("Byte string is not UTF-8.");
    }
  }

  @Test
  public void testBuildRepeatedStringWithBadUtf8Size() throws Exception {
    try {
      StringWrapperSize.newBuilder().addRepBytes(NON_UTF8_BYTE_STRING);
      assertWithMessage("Expected IllegalArgumentException for non UTF-8 byte string.").fail();
    } catch (IllegalArgumentException exception) {
      assertThat(exception).hasMessageThat().isEqualTo("Byte string is not UTF-8.");
    }
  }

  @Test
  public void testParseRequiredStringWithBadUtf8Size() throws Exception {
    byte[] serialized =
        BytesWrapperSize.newBuilder().setReq(NON_UTF8_BYTE_STRING).build().toByteArray();
    assertParseBadUtf8(StringWrapperSize.getDefaultInstance(), serialized);
  }

  private void assertParseBadUtf8(MessageLite defaultInstance, byte[] data) throws Exception {
    // Check combinations of (parser vs. builder) x (byte[] vs. InputStream)
    try {
      defaultInstance.getParserForType().parseFrom(data);
      assertWithMessage("Expected InvalidProtocolBufferException for non UTF-8 byte string.")
          .fail();
    } catch (InvalidProtocolBufferException exception) {
      assertThat(exception).hasMessageThat().isEqualTo("Protocol message had invalid UTF-8.");
    }
    try {
      defaultInstance.newBuilderForType().mergeFrom(data);
      assertWithMessage("Expected InvalidProtocolBufferException for non UTF-8 byte string.")
          .fail();
    } catch (InvalidProtocolBufferException exception) {
      assertThat(exception).hasMessageThat().isEqualTo("Protocol message had invalid UTF-8.");
    }
    try {
      defaultInstance.getParserForType().parseFrom(new ByteArrayInputStream(data));
      assertWithMessage("Expected InvalidProtocolBufferException for non UTF-8 byte string.")
          .fail();
    } catch (InvalidProtocolBufferException exception) {
      assertThat(exception).hasMessageThat().isEqualTo("Protocol message had invalid UTF-8.");
    }
    try {
      defaultInstance.newBuilderForType().mergeFrom(new ByteArrayInputStream(data));
      assertWithMessage("Expected InvalidProtocolBufferException for non UTF-8 byte string.")
          .fail();
    } catch (InvalidProtocolBufferException exception) {
      assertThat(exception).hasMessageThat().isEqualTo("Protocol message had invalid UTF-8.");
    }
  }
}
