require linux-intel.inc

KBRANCH = "5.15/linux"
KMETA_BRANCH = "yocto-5.15"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

SRC_URI:append = " file://0001-menuconfig-mconf-cfg-Allow-specification-of-ncurses-.patch \
                   "

DEPENDS += "elfutils-native openssl-native util-linux-native"

LINUX_VERSION ?= "5.15.1"
SRCREV_machine ?= "d7d7ea689cc887081dd8bf8ef2f296b5cd9c593e"
SRCREV_meta ?= "bee5d6a15909f05935f4a61f83a72cddfca7934a"

# For Crystalforest and Romley
KERNEL_MODULE_AUTOLOAD:append:core2-32-intel-common = " uio"
KERNEL_MODULE_AUTOLOAD:append:corei7-64-intel-common = " uio"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc features/security/security.scc"

# Disabling CONFIG_SND_SOC_INTEL_SKYLAKE for 32-bit, does not allow to set CONFIG_SND_SOC_INTEL_SST too, which
# causes config warning too.
KCONF_AUDIT_LEVEL:core2-32-intel-common = "0"
