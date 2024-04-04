# SAP CX Intelligence & Incubation Commerce Extension

[![REUSE status](https://api.reuse.software/badge/github.com/SAP-samples/cxii-commerce-extn)](https://api.reuse.software/info/github.com/SAP-samples/cxii-commerce-extn)

## Description
SAP CX Intelligence & Incubation (CXII) Commerce extension is required to configure the tenant credentials in Commerce Backoffice.
This is required for enabling configurations for CXII UI components like SAP-samples/cxii-visual-search-ui-component

## Requirements
SAP Commerce Environment
SAP Commerce Administration Access

## Download and Installation
1. Clone the CXAI extension repo `https://github.com/SAP-samples/cxii-commerce-extn` to the commerce extensions.
    Ex: `hybris/bin/custom/<storefront-app>/cxai`
2. Navigate to `hybris/config` folder and add the following entries to the `localextensions.xml` file
    a. <extension name='cxaibackoffice' />
    b. <extension name='cxaiocc' />
3. Add the following entries to `bin/custom/<app>/ci/recipes/cx-spa/build.gradle` file. These entries will help build the extension sources
    a. extName 'cxaibackoffice'
    b. extName 'cxaiocc'
4. Now follow the build steps and deployment steps as per the instructions for the `storefront` app deployed in commerce `hybris/bin/custom/<app>`

## Configuration
1. Once the build and deployment is successful, restart the commerce server.
2. Now navigate to backoffice/administration cockpit.
3. Click on `CX AI` extension in left navigation tree.
4. Create the configuration with the tenant's Consumed Destination and OAuth details.
5. Custome CatalogId is optional.
## Known Issues
No known issues at this time
<!-- You may simply state "No known issues. -->

## How to obtain support
[Create an issue](https://github.com/SAP-samples/cxii-commerce-extn/issues) in this repository if you find a bug or have questions about the content.
 
For additional support, [ask a question in SAP Community](https://answers.sap.com/questions/ask.html).

## Contributing
If you wish to contribute code, offer fixes or improvements, please send a pull request. Due to legal reasons, contributors will be asked to accept a DCO when they create the first pull request to this project. This happens in an automated fashion during the submission process. SAP uses [the standard DCO text of the Linux Foundation](https://developercertificate.org/).

## License
Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved. This project is licensed under the Apache Software License, version 2.0 except as noted otherwise in the [LICENSE](LICENSE) file.
