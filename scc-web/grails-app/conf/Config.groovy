import org.apache.log4j.DailyRollingFileAppender

// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination

// The ACCEPT header will not be used for content negotiation for user agents containing the following strings (defaults to the 4 major rendering engines)
grails.mime.disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
grails.mime.types = [ // the first one is the default format
	all:           '*/*', // 'all' maps to '*' or the first available format in withFormat
	atom:          'application/atom+xml',
	css:           'text/css',
	csv:           'text/csv',
	form:          'application/x-www-form-urlencoded',
	html:          ['text/html','application/xhtml+xml'],
	js:            'text/javascript',
	json:          ['application/json', 'text/json'],
	multipartForm: 'multipart/form-data',
	rss:           'application/rss+xml',
	text:          'text/plain',
	hal:           ['application/hal+json','application/hal+xml'],
	xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// Legacy setting for codec used to encode data with ${}
grails.views.default.codec = "html"

grails.project.war.file = "target/${appName}.war"

// The default scope for controllers. May be prototype, session or singleton.
// If unspecified, controllers are prototype scoped.
grails.controllers.defaultScope = 'singleton'

// GSP settings
grails {
	views {
		gsp {
			encoding = 'UTF-8'
			htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
			codecs {
				expression = 'html' // escapes values inside ${}
				scriptlet = 'html' // escapes output from scriptlets in GSPs
				taglib = 'none' // escapes output from taglibs
				staticparts = 'none' // escapes output from static template parts
			}
		}
		// escapes all not-encoded output at final stage of outputting
		// filteringCodecForContentType.'text/html' = 'html'
	}
}


grails.converters.encoding = "UTF-8"
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method

grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

// configure passing transaction's read-only attribute to Hibernate session, queries and criterias
// set "singleSession = false" OSIV mode in hibernate configuration after enabling
grails.hibernate.pass.readonly = false
// configure passing read-only to OSIV session by default, requires "singleSession = false" OSIV mode
grails.hibernate.osiv.readonly = false

//searchpool configurations
com.freddiemac.searchpool.url="http://localhost:9999/freddiemac/searchpool.asmx"
com.freddiemac.searchpool.result.path="SearchPoolResponse.ResponseEnvelope.SearchResultContainer"
com.freddiemac.searchpool.error.path="SearcPoolResponse.ResponseEnvelope.ErrorEnvelope"
com.freddiemac.searchpool.result.elements=["Pool.PoolIdentifier","Security.SecurityCUSIPIdentifier","Pool.PoolType","Loan.LoanAmortizationType","LoanPosition.LoanGrossUPBAmount","MBS.MBSMortgageType","MBS.MBSPoolPrefixCode","SecurityWire.SecurityWireFaceValueAmount","SecurityIssuance.SecurityIssueDate","Security.ContractualMaturityDate","SecurityActivity.SecurityCouponRate","SecurityActivity.SecurityBeneficiaryDate","SecurityActivity.SecurityStatusType"]
com.freddiemac.searchpool.result.dissolve.elements=["PartyRole.PartyRoleType", "SecurityIssuer.SecurityIssuerIdentifier", "SecurityWire.SecurityWireFaceValueAmount", "WireTransferTransaction.WireTotalFeesAmount", "WireTransferTransaction.WireInstructionExecutionDate", "CashTransferInstruction.CashTransferInstructionIdentifier", "Loan.LoanIdentifier", "FinancialInstrument.FinancialInstrumentType"]
com.freddiemac.searchpool.result.xfields=["LoanRelationship.LoanRoleType","MBS.MBSAmortizationType","MBSDisclosure.MBSBalloonTermCount","MBS.InterestOnlyTermCount","MBS.MBSExecutionMethodType","Security.FederalReserveSecurityClassCode","Security.FederalReserveSecurityDescription","SecurityIssuance.SecurityIssueFaceAmount","Security.SecurityFirstPaymentDate","Security.MinimumTradeDenominationAmount","Security.AssetBackedSecurityType","GuarantyFeeGrid.ProgramIdentifier","LoanPaymentRate.LoanInvestorPassThroughRate","SecurityActivity.SecurityRecordDate","MBSDisclosure.MBSInterestPaymentFactorRate","CollateralGroup.SecuritizationTrustIdentifier","Security.SecurityPayingAgentIdentifier","SecurityActivity.SecurityTaxRecordDate","BulkLoanPurchaseDeal.SecurityIssuerIdentifier","ContractCounterparty.PartyRoleType","FinancialInstrument.FinancialInstrumentType"]
com.freddiemac.onedotfive.fields = [
	"PoolInstrument.PoolIdentifier",
	"Pool.PoolType",
	'Sender.FinancialInstitution.FinancialInstitutionTelegraphicAbbreviationName',
	'Sender.FinancialInstitution.ABARoutingAndTransitIdentifier',
	'Sender.Organization.OrganizationName',
	'Sender.FinancialInstitutionAccount.FinancialInstitutionAccountSubaccountName',
	'Receiver.FinancialInstitution.FinancialInstitutionTelegraphicAbbreviationName',
	'Receiver.FinancialInstitution.ABARoutingAndTransitIdentifier',
	'Receiver.Organization.OrganizationName',
	'Receiver.FinancialInstitutionAccount.FinancialInstitutionAccountSubaccountName',
	'SecurityWire.SecurityWireFaceValueAmount',
	'TransferFee.TransferFeeAmount',
	'PartyRole.PartyRoleType',
	'SecurityIssuer.SecurityIssuerIdentifier',
	'WireTransferTransaction.WireTotalFeesAmount',
	'WireTransferTransaction.WireInstructionExecutionDate',
	'CashTransferInstruction.CashTransferInstructionIdentifier',
	'FinancialInstrument.FinancialInstrumentType',
	'Loan.LoanIdentifier'
]

com.freddiemac.searchpool.result.wireinstructions=[
	"FinancialInstitution.ABARoutingAndTransitIdentifier",
	"FinancialInstitutionAccount.FinancialInstitutionAccountSubaccountName",
	"Organization.OrganizationName",
	"FinancialInstitution.FinancialInstitutionTelegraphicAbbreviationName",
	"FinancialInstitutionAccount.FinancialInstitutionAccountIdentifier"
]
com.freddiemac.searchpool.result.poolid = "Pool.PoolIdentifier"
com.freddiemac.searchpool.result.cusip = "Security.SecurityCUSIPIdentifier"
com.freddiemac.searchpool.result.securityissuedate = "SecurityIssuance.SecurityIssueDate"
com.freddiemac.searchpool.result.securitysettledate = "SecurityIssuance.SecurityIssueSettlementDate"
com.freddiemac.searchpool.result.pooltype = "Pool.PoolType"

environments {
	development {
		grails.logging.jul.usebridge = true
	}
	production {
		grails.logging.jul.usebridge = false
		// TODO: grails.serverURL = "http://www.changeme.com"
	}
}

log4j = {
    appenders {
        environments {
            development {
                console name: 'stdout', layout: pattern(conversionPattern: '%-5p %d{HH:mm:ss,SSS} %c{2} %m%n')
            }

            production {
                appender new DailyRollingFileAppender(
                        name: 'file',
                        datePattern: "'.'yyyy-MM-dd",
                        file: System.properties['catalina.base'] + "/logs/scc.log",
                        layout: pattern(conversionPattern: '%d [%t] %-5p %c{2} %x - %m%n')
                )
                rollingFile name: "stacktrace", maxFileSize: 1024 * 10,
                        file: System.properties['catalina.base'] + "/logs/scc.log"
            }
        }
    }

    info 'grails.app.conf'
    info 'grails.app.jobs'
    info 'grails.app.services'
    info 'grails.app.controllers'
    info 'com.freddiemac.scc'

    error 'org.codehaus.groovy.grails.web.servlet',        // controllers
            'org.codehaus.groovy.grails.web.pages',          // GSP
            'org.codehaus.groovy.grails.web.sitemesh',       // layouts
            'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
            'org.codehaus.groovy.grails.web.mapping',        // URL mapping
            'org.codehaus.groovy.grails.commons',            // core / classloading
            'org.codehaus.groovy.grails.plugins',            // plugins
            'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
            'org.springframework',
            'org.hibernate',
            'net.sf.ehcache.hibernate'
            'org.hibernate'


    environments {
        development {
            info 'grails.app.conf'
            info 'grails.app.services'
            info 'grails.app.controllers'
            debug 'com.freddiema.scc'
            info stdout: "StackTrace"
        }
        production {
            info 'grails.app.jobs'
            info 'grails.app.services'
            info 'grails.app.controllers'
            debug 'com.freddiemac.scc'


            root {
                error 'file'
            }
        }
    }
}


