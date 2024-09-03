"""A function to compile C/C++ code, like cc_library() but from Starlark."""

load("@bazel_tools//tools/cpp:toolchain_utils.bzl", "find_cpp_toolchain", "use_cpp_toolchain")

# begin:google_only
#
# def upb_use_cpp_toolchain():
#     # TODO: We shouldn't need to add this to the result of use_cpp_toolchain().
#     return [
#         config_common.toolchain_type(
#             "@bazel_tools//tools/cpp:cc_runtimes_toolchain_type",
#             mandatory = False,
#         ),
#     ] + use_cpp_toolchain()
#
# end:google_only

# begin:github_only
def upb_use_cpp_toolchain():
    return use_cpp_toolchain()

# end:github_only

def cc_library_func(ctx, name, hdrs, srcs, copts, includes, dep_ccinfos):
    """Like cc_library(), but callable from rules.

    Args:
      ctx: Rule context.
      name: Unique name used to generate output files.
      hdrs: Public headers that can be #included from other rules.
      srcs: C/C++ source files.
      copts: Additional options for cc compilation.
      includes: Additional include paths.
      dep_ccinfos: CcInfo providers of dependencies we should build/link against.

    Returns:
      CcInfo provider for this compilation.
    """

    # begin:google_only
    #     cc_runtimes_toolchain = ctx.toolchains["@bazel_tools//tools/cpp:cc_runtimes_toolchain_type"]
    #     if cc_runtimes_toolchain:
    #         dep_ccinfos += [
    #             target[CcInfo]
    #             for target in cc_runtimes_toolchain.cc_runtimes_info.runtimes
    #         ]
    #
    # end:google_only

    compilation_contexts = [info.compilation_context for info in dep_ccinfos]
    linking_contexts = [info.linking_context for info in dep_ccinfos]
    toolchain = find_cpp_toolchain(ctx)
    feature_configuration = cc_common.configure_features(
        ctx = ctx,
        cc_toolchain = toolchain,
        requested_features = ctx.features,
        unsupported_features = ctx.disabled_features,
    )

    (compilation_context, compilation_outputs) = cc_common.compile(
        actions = ctx.actions,
        feature_configuration = feature_configuration,
        cc_toolchain = toolchain,
        name = name,
        srcs = srcs,
        includes = includes,
        public_hdrs = hdrs,
        user_compile_flags = copts,
        compilation_contexts = compilation_contexts,
    )

    # buildifier: disable=unused-variable
    (linking_context, linking_outputs) = cc_common.create_linking_context_from_compilation_outputs(
        actions = ctx.actions,
        name = name,
        feature_configuration = feature_configuration,
        cc_toolchain = toolchain,
        compilation_outputs = compilation_outputs,
        linking_contexts = linking_contexts,
        disallow_dynamic_library = cc_common.is_enabled(feature_configuration = feature_configuration, feature_name = "targets_windows") or not cc_common.is_enabled(feature_configuration = feature_configuration, feature_name = "supports_dynamic_linker"),
    )

    return CcInfo(
        compilation_context = compilation_context,
        linking_context = linking_context,
    )
