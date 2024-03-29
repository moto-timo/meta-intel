SUMMARY = "oneAPI Video Processing Library"
DESCRIPTION = "The oneAPI Video Processing Library (oneVPL) provides \
a single video processing API for encode, decode, and video processing \
that works across a wide range of accelerators."

HOMEPAGE = "https://github.com/oneapi-src/oneVPL"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c18ea6bb4786a26bf4eee88a7424a408 \
                    file://third-party-programs.txt;md5=67727fd253b1b4574cd9eea78a2b0620"

SRC_URI = "git://github.com/oneapi-src/oneVPL.git;protocol=https;branch=master \
            file://0001-Fix-basename-build-issue-with-musl_libc.patch \
            file://0001-Extends-errorTypes-to-support-JPEG-errors.patch \
            file://0001-samples-Addin-wayland-scanner-auto-generate-on-cmake.patch \
            file://0001-sample_misc-Addin-basic-wayland-dmabuf-support.patch \
            file://0001-sample_misc-use-wayland-dmabuf-to-render-nv12.patch \
            file://0001-samples-use-find_program-to-detect-wayland-scanner-i.patch \
            file://0001-sample_decode-Add-VDSFC-CSC-for-AVC-HEVC.patch \
            "
SRCREV = "cdf7444dc971544d148c51e0d93a2df1bb55dda7"
S = "${WORKDIR}/git"

inherit cmake
DEPENDS += "libva pkgconfig-native"

PACKAGECONFIG ??= "tools"
PACKAGECONFIG[tools] = "-DBUILD_TOOLS=ON, -DBUILD_TOOLS=OFF, wayland wayland-native wayland-protocols"

do_install:append() {
        mkdir -p ${D}${datadir}/oneVPL/samples
        mv ${D}${bindir}/sample_* ${D}${datadir}/oneVPL/samples
}

COMPATIBLE_HOST = '(x86_64).*-linux'

PACKAGES += "${PN}-examples"

FILES:${PN}-examples = "${datadir}/oneVPL/examples \
                       "

FILES_SOLIBSDEV = ""
FILES:${PN}-dev += "${libdir}/libvpl.so"

FILES:${PN} += "${datadir} \
                ${libdir}/oneVPL/libvpl_wayland.so \
                "
