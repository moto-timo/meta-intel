SUMMARY = "The Intel(R) Graphics Compute Runtime for OpenCL(TM)"
DESCRIPTION = "The Intel(R) Graphics Compute Runtime for OpenCL(TM) \
is an open source project to converge Intel's development efforts \
on OpenCL(TM) compute stacks supporting the GEN graphics hardware \
architecture."

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=983b0c493ea3dc3c21a90ff743bf90e4 \
                    file://third_party/opencl_headers/LICENSE;md5=dcefc90f4c3c689ec0c2489064e7273b"

SRC_URI = "git://github.com/intel/compute-runtime.git;protocol=https;branch=master \
          "

SRC_URI:append:class-target = "file://allow-to-find-cpp-generation-tool.patch"

SRCREV = "3269e719a3ee7bcd97c50ec2cfe78fc8674adec0"

S = "${WORKDIR}/git"

DEPENDS += " intel-graphics-compiler gmmlib"
DEPENDS:append:class-target = " intel-compute-runtime-native libva"

RDEPENDS:${PN} += " intel-graphics-compiler gmmlib"

inherit cmake pkgconfig

COMPATIBLE_HOST = '(x86_64).*-linux'
COMPATIBLE_HOST:libc-musl = "null"

EXTRA_OECMAKE = " \
                 -DIGC_DIR=${STAGING_INCDIR}/igc \
                 -DBUILD_TYPE=Release \
                 -DSKIP_UNIT_TESTS=1 \
                 -DCCACHE_ALLOWED=FALSE \
                 -DNEO_DISABLE_LD_LLD=ON \
                 -DNEO_DISABLE_LD_GOLD=ON \
                 "
EXTRA_OECMAKE:append:class-native = " -DNEO_DISABLE_BUILTINS_COMPILATION=ON"

EXTRA_OECMAKE:append:class-target = " \
                                     -Dcloc_cmd_prefix=ocloc \
                                    "

PACKAGECONFIG ??= ""
PACKAGECONFIG[levelzero] = "-DBUILD_WITH_L0=ON, -DBUILD_WITH_L0=OFF, level-zero"

do_install:append:class-native() {
    install -d ${D}${bindir}
    install ${B}/bin/cpp_generate_tool ${D}${bindir}/
}

FILES:${PN} += " \
                 ${libdir}/intel-opencl/libigdrcl.so \
                 ${libdir}/libocloc.so \
                 "

FILES:${PN}-dev = "${includedir}"

BBCLASSEXTEND = "native nativesdk"

UPSTREAM_CHECK_GITTAGREGEX = "(?P<pver>\d+(\.\d+)+)"
