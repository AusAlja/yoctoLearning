SUMMARY = "pigpio GPIO control library for Raspberry Pi"
DESCRIPTION = "pigpio is a C library and daemon for GPIO control on Raspberry Pi"
SECTION = "utils"

LICENSE = "Unlicense"
LIC_FILES_CHKSUM = "file://UNLICENCE;md5=61287f92700ec1bdf13bc86d8228cd13"

SRC_URI = "git://github.com/joan2937/pigpio.git;protocol=https;branch=master"
SRCREV = "c33738a320a3e28824af7807edafda440952c05d"

S = "${WORKDIR}/git"

EXTRA_OEMAKE += "CC='${CC}'"
EXTRA_OEMAKE += "CROSS_PREFIX=${TARGET_PREFIX}"
EXTRA_OEMAKE += "STRIP=echo"
TARGET_CC_ARCH += "${LDFLAGS}"

# main package intentionally empty
ALLOW_EMPTY:${PN} = "1"

do_install() {
    oe_runmake install \
        DESTDIR=${D} \
        prefix=${prefix} \
        mandir=${mandir}

    # upstream garbage (not Yocto-friendly)
    rm -rf ${D}/usr/local
    rm -rf ${D}/opt
}

inherit lib_package

