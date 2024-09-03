// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// clang-format off
// source: google/protobuf/wrappers.proto

#import "GPBDescriptor.h"
#import "GPBMessage.h"
#import "GPBRootObject.h"

#if GOOGLE_PROTOBUF_OBJC_VERSION < 30007
#error This file was generated by a newer version of protoc which is incompatible with your Protocol Buffer library sources.
#endif
#if 30007 < GOOGLE_PROTOBUF_OBJC_MIN_SUPPORTED_VERSION
#error This file was generated by an older version of protoc which is incompatible with your Protocol Buffer library sources.
#endif

// @@protoc_insertion_point(imports)

#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wdeprecated-declarations"

CF_EXTERN_C_BEGIN

NS_ASSUME_NONNULL_BEGIN

#pragma mark - GPBWrappersRoot

/**
 * Exposes the extension registry for this file.
 *
 * The base class provides:
 * @code
 *   + (GPBExtensionRegistry *)extensionRegistry;
 * @endcode
 * which is a @c GPBExtensionRegistry that includes all the extensions defined by
 * this file and all files that it depends on.
 **/
GPB_FINAL @interface GPBWrappersRoot : GPBRootObject
@end

#pragma mark - GPBDoubleValue

typedef GPB_ENUM(GPBDoubleValue_FieldNumber) {
  GPBDoubleValue_FieldNumber_Value = 1,
};

/**
 * Wrapper message for `double`.
 *
 * The JSON representation for `DoubleValue` is JSON number.
 **/
GPB_FINAL @interface GPBDoubleValue : GPBMessage

/** The double value. */
@property(nonatomic, readwrite) double value;

@end

#pragma mark - GPBFloatValue

typedef GPB_ENUM(GPBFloatValue_FieldNumber) {
  GPBFloatValue_FieldNumber_Value = 1,
};

/**
 * Wrapper message for `float`.
 *
 * The JSON representation for `FloatValue` is JSON number.
 **/
GPB_FINAL @interface GPBFloatValue : GPBMessage

/** The float value. */
@property(nonatomic, readwrite) float value;

@end

#pragma mark - GPBInt64Value

typedef GPB_ENUM(GPBInt64Value_FieldNumber) {
  GPBInt64Value_FieldNumber_Value = 1,
};

/**
 * Wrapper message for `int64`.
 *
 * The JSON representation for `Int64Value` is JSON string.
 **/
GPB_FINAL @interface GPBInt64Value : GPBMessage

/** The int64 value. */
@property(nonatomic, readwrite) int64_t value;

@end

#pragma mark - GPBUInt64Value

typedef GPB_ENUM(GPBUInt64Value_FieldNumber) {
  GPBUInt64Value_FieldNumber_Value = 1,
};

/**
 * Wrapper message for `uint64`.
 *
 * The JSON representation for `UInt64Value` is JSON string.
 **/
GPB_FINAL @interface GPBUInt64Value : GPBMessage

/** The uint64 value. */
@property(nonatomic, readwrite) uint64_t value;

@end

#pragma mark - GPBInt32Value

typedef GPB_ENUM(GPBInt32Value_FieldNumber) {
  GPBInt32Value_FieldNumber_Value = 1,
};

/**
 * Wrapper message for `int32`.
 *
 * The JSON representation for `Int32Value` is JSON number.
 **/
GPB_FINAL @interface GPBInt32Value : GPBMessage

/** The int32 value. */
@property(nonatomic, readwrite) int32_t value;

@end

#pragma mark - GPBUInt32Value

typedef GPB_ENUM(GPBUInt32Value_FieldNumber) {
  GPBUInt32Value_FieldNumber_Value = 1,
};

/**
 * Wrapper message for `uint32`.
 *
 * The JSON representation for `UInt32Value` is JSON number.
 **/
GPB_FINAL @interface GPBUInt32Value : GPBMessage

/** The uint32 value. */
@property(nonatomic, readwrite) uint32_t value;

@end

#pragma mark - GPBBoolValue

typedef GPB_ENUM(GPBBoolValue_FieldNumber) {
  GPBBoolValue_FieldNumber_Value = 1,
};

/**
 * Wrapper message for `bool`.
 *
 * The JSON representation for `BoolValue` is JSON `true` and `false`.
 **/
GPB_FINAL @interface GPBBoolValue : GPBMessage

/** The bool value. */
@property(nonatomic, readwrite) BOOL value;

@end

#pragma mark - GPBStringValue

typedef GPB_ENUM(GPBStringValue_FieldNumber) {
  GPBStringValue_FieldNumber_Value = 1,
};

/**
 * Wrapper message for `string`.
 *
 * The JSON representation for `StringValue` is JSON string.
 **/
GPB_FINAL @interface GPBStringValue : GPBMessage

/** The string value. */
@property(nonatomic, readwrite, copy, null_resettable) NSString *value;

@end

#pragma mark - GPBBytesValue

typedef GPB_ENUM(GPBBytesValue_FieldNumber) {
  GPBBytesValue_FieldNumber_Value = 1,
};

/**
 * Wrapper message for `bytes`.
 *
 * The JSON representation for `BytesValue` is JSON string.
 **/
GPB_FINAL @interface GPBBytesValue : GPBMessage

/** The bytes value. */
@property(nonatomic, readwrite, copy, null_resettable) NSData *value;

@end

NS_ASSUME_NONNULL_END

CF_EXTERN_C_END

#pragma clang diagnostic pop

// @@protoc_insertion_point(global_scope)

// clang-format on
