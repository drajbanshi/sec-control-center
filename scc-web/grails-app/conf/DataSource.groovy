dataSource {
	pooled = true
	jmxExport = true
	driverClassName = "org.h2.Driver"
	//driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
	username = "sa"
	password = ""
}
dataSource_sm {
	pooled = true
	jmxExport = true
	//driverClassName = "org.h2.Driver"
	driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
	username = "smgddbs0"
	password = "Feb2015!"
}
hibernate {
	cache.use_second_level_cache = true
	cache.use_query_cache = false
	//    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
	cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
	singleSession = true // configure OSIV singleSession mode
	flush.mode = 'manual' // OSIV session flush mode outside of transactional context
	//dialect = "org.hibernate.dialect.SQLServerDialect"
}
 
// environment specific settings
environments {
	development {
		dataSource {
			dbCreate = "update"
			url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
			driverClassName = "org.h2.Driver"
			username = "sa"
			password = ""
		}
		dataSource_sm{
			dbCreate = "none"
			url = "jdbc:sqlserver://he3qntvdsql013:1433;databaseName=secmgrdb"
			driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
			username = "smgddbs0"
			password = "Feb2015!"
		}
	}
	test {
		dataSource {
			dbCreate = "update"
			url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
			driverClassName = "org.h2.Driver"
			username = "sa"
			password = ""
		}
		dataSource_sm{
			dbCreate = "none"
			url = "jdbc:sqlserver://he3qntvdsql013:1433;databaseName=secmgrdb"
			driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
			username = "smgddbs0"
			password = "Feb2015!"
		}
	}
	production {
		dataSource {
			dbCreate = "update"
			url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
			driverClassName = "org.h2.Driver"
			username = "sa"
			password = ""
		}
	}
}
 