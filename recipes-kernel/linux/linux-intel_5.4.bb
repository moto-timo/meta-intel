require linux-intel.inc

KBRANCH = "5.4/yocto"
KMETA_BRANCH = "yocto-5.4"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

SRC_URI_append = " file://0001-menuconfig-mconf-cfg-Allow-specification-of-ncurses-.patch"

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION ?= "5.4.170"
SRCREV_machine ?= "36f93ff941f127f4137ab369aecbdd995fb58c66"
SRCREV_meta ?= "98cce1c95fcc9a26965cbc5f038fd71d53c387c8"

# For Crystalforest and Romley
KERNEL_MODULE_AUTOLOAD_append_core2-32-intel-common = " uio"
KERNEL_MODULE_AUTOLOAD_append_corei7-64-intel-common = " uio"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc features/security/security.scc"
