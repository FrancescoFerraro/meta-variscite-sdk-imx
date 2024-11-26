SUMMARY = "Variscite Package Group for i.MX Machines"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PACKAGES = "\
    ${PN}-devel \
    ${PN}-docker \
    ${PN}-ml \
    ${PN}-security \
"

RDEPENDS:${PN}-devel = "\
    libgpiod \
    libgpiod-tools \
    nodejs \
    openssh-sftp-server \
    screen \
    tcf-agent \
    devmem2 \
"

# Only for DRM enabled machines
RDEPENDS:${PN}-devel:append:imxdrm = " \
    libdrm-tests \
"

RDEPENDS:${PN}-docker:mx8-nxp-bsp = "\
    docker-moby \
    python3-docker-compose \
"

RDEPENDS:${PN}-docker:mx9-nxp-bsp = "\
    docker-moby \
    python3-docker-compose \
"

# Machine Learning package for i.MX8 machines
RDEPENDS:${PN}-ml:mx8-nxp-bsp = "\
    python3-opencv \
    python3-pip \
    python3-requests \
    python3-sympy \
    packagegroup-imx-ml \
"

# Machine Learning package for i.MX9 machines
RDEPENDS:${PN}-ml:mx9-nxp-bsp = "\
    python3-opencv \
    python3-pip \
    python3-requests \
    python3-sympy \
    packagegroup-imx-ml \
"

RDEPENDS:${PN}-security = "\
    keyctl-caam \
    keyutils \
    lvm2 \
"
