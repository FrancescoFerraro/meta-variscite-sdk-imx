# Description: Configuration file to include AI/ML packages for different BSP versions.
# This configuration follows best practices to manage dependencies and ensure compatibility across different platforms.

DESCRIPTION = "Add packages for AI/ML build"
PACKAGE_ARCH = "${TUNE_PKGARCH}"

# Inherit packagegroup to handle grouping of packages
inherit packagegroup

# Define Machine Learning packages for different platforms
ML_PKGS ?= ""

# Packages for MX8 platform
ML_PKGS:mx8-nxp-bsp = " \
    pytorch \
    tensorflow-lite \
    tensorflow-lite-vx-delegate \
    torchvision \
    arm-compute-library \
"

# Additional packages for MX8MP
ML_PKGS:append:mx8mp-nxp-bsp = " \
    tvm \
    arm-compute-library \
"

# Remove specific package for MX8MM
ML_PKGS:remove:mx8mm-nxp-bsp = "tensorflow-lite-vx-delegate"

# Define extra ML packages for MX8 platform
ML_EXTRA_PKGS ?= ""
ML_EXTRA_PKGS:mx8-nxp-bsp = " \
    python3-opencv \
    python3-pip \
    python3-requests \
    python3-sympy \
"

# Define Machine Learning packages for MX9 platform
ML_PKGS:mx9-nxp-bsp = " \
    onnxruntime-tests \
    tensorflow-lite \
    arm-compute-library \
"

# ARM Ethos-U packages for MX93
ETHOS_U_PKGS = ""
ETHOS_U_PKGS:mx93-nxp-bsp = " \
    ethos-u-vela \
    ethos-u-driver-stack \
    tensorflow-lite-ethosu-delegate \
    eiq-examples \
"

# Neutron package for MX95
NEUTRON_PKGS = ""
NEUTRON_PKGS:mx95-nxp-bsp = " \
    neutron \
    tensorflow-lite-neutron-delegate \
"

# Define accelerated packages that are only applicable to devices with GPU support
ML_ACCELERATED_PKGS = ""
ML_ACCELERATED_PKGS:mx8-nxp-bsp:imxgpu = " \
    tensorflow-lite-vx-delegate \
"
ML_ACCELERATED_PKGS:mx8mp-nxp-bsp = " \
    tensorflow-lite-vx-delegate \
    tvm \
"
ML_ACCELERATED_PKGS:mx8mm-nxp-bsp = ""

# NNStreamer package list definition
ML_NNSTREAMER_PKGS_LIST = " \
    nnstreamer \
    nnstreamer-protobuf \
    nnstreamer-python3 \
    nnstreamer-query \
    nnstreamer-tensorflow-lite \
"

# NNStreamer packages for different platforms
ML_NNSTREAMER_PKGS = ""
ML_NNSTREAMER_PKGS:mx8-nxp-bsp:imxgpu = "${ML_NNSTREAMER_PKGS_LIST}"
ML_NNSTREAMER_PKGS:mx8mp-nxp-bsp      = "${ML_NNSTREAMER_PKGS_LIST} nnstreamer-tvm nnstreamer-unittest"
ML_NNSTREAMER_PKGS:mx9-nxp-bsp        = "${ML_NNSTREAMER_PKGS_LIST}"

# Profiling tools specific to platforms
ML_GST_PROFILER = ""
ML_GST_PROFILER:mx8-nxp-bsp   = "gst-shark"
ML_GST_PROFILER:mx8mp-nxp-bsp = "nnshark"
ML_GST_PROFILER:mx9-nxp-bsp   = "gst-shark"

# Establish runtime dependencies including all defined package variables
RDEPENDS:${PN} = " \
    ${ML_PKGS} \
    ${ML_EXTRA_PKGS} \
    ${ETHOS_U_PKGS} \
    ${NEUTRON_PKGS} \
    ${ML_ACCELERATED_PKGS} \
    ${ML_NNSTREAMER_PKGS} \
    ${ML_GST_PROFILER} \
"
