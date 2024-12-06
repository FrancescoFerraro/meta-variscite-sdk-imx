SUMMARY = "Variscite target Yocto install for recovery images."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# For historical reasons, scripts are located outside ${PN} directory
FILESEXTRAPATHS:prepend := "${THISDIR}/../../scripts/var_mk_yocto_sdcard/variscite_scripts:"

INSTALL_SCRIPT:imx6ul-var-dart ?= "mx6ul_mx7_install_yocto.sh"
INSTALL_SCRIPT:var-som-mx6 ?= "mx6_install_yocto.sh"
INSTALL_SCRIPT:mx7-nxp-bsp ?= "mx6ul_mx7_install_yocto.sh"
INSTALL_SCRIPT:mx8-nxp-bsp ?= "mx8_install_yocto.sh"
INSTALL_SCRIPT:mx9-nxp-bsp ?= "mx8_install_yocto.sh"

INSTALL_SCRIPT_NAME ?= "install_yocto.sh"

SRC_URI = "\
    file://${INSTALL_SCRIPT} \
    file://echos.sh \
    file://mx6_install_yocto_emmc.sh \
"

do_install() {
    install -Dm 0755 ${WORKDIR}/${INSTALL_SCRIPT} ${D}${bindir}/${INSTALL_SCRIPT_NAME}
    install -Dm 0755 ${WORKDIR}/echos.sh ${D}${bindir}/echos.sh
}

do_install:append:var-som-mx6() {
    install -Dm 0755 ${WORKDIR}/mx6_install_yocto_emmc.sh ${D}${bindir}/install_yocto_emmc.sh
}

FILES:${PN} = "\
    ${bindir} \
"

IMX6_7_EXTRA_RDEPENDS = "\
    imx-kobs \
    dosfstools \
    i2c-tools \
    libubootenv \
    mtd-utils \
    mtd-utils-ubifs \
"

RDEPENDS:${PN} = "\
    bash \
    e2fsprogs-e2fsck \
    e2fsprogs-mke2fs \
    tar \
    util-linux-fdisk \
    zstd \
"

RDEPENDS:${PN}:append:mx6-nxp-bsp = "${IMX6_7_EXTRA_RDEPENDS}"
RDEPENDS:${PN}:append:mx7-nxp-bsp = "${IMX6_7_EXTRA_RDEPENDS}"

COMPATIBLE_MACHINE = "(mx6-nxp-bsp|mx7-nxp-bsp|mx8-nxp-bsp|mx9-nxp-bsp)"
