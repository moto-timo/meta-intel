SUMMARY = "Applications and Scripts for libyami."
DESCRIPTION = "Applications and Scripts for libyami."

HOMEPAGE = "https://github.com/intel/libyami-utils"
BUGTRACKER = "https://github.com/intel/libyami-utils/issues/new"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

SRC_URI = "git://github.com/intel/libyami-utils.git;branch=master;protocol=https \
           file://0001-Fix-build-with-clang.patch \
           "
SRCREV = "c3d25b64b05aeb0c4eecc140aef617cfeced6b8e"
S = "${WORKDIR}/git"

DEPENDS = "libva libyami"

inherit autotools pkgconfig features_check

REQUIRED_DISTRO_FEATURES = "opengl"

PACKAGECONFIG = "${@bb.utils.filter('DISTRO_FEATURES', 'x11', d)}"

# --enable-x11 needs libva-x11
# gles-tests fail to build without x11: see https://github.com/intel/libyami-utils/issues/91
PACKAGECONFIG[x11] = "--enable-x11 --enable-egl,--disable-x11 --disable-egl, virtual/libx11"
