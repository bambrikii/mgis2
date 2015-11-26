# mgis2

## To run the app


1. apt-get install git jdk1.8 tomcat8 postgresql postgresql-contrib postgis maven node
2. clone mgis2 repository;
3. generate your private/public key, add a public fingerprint to ssh://192.168.11.196:/home/maven/.ssh/authorized_keys
4. configure maven settings file according to the sample:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                          http://maven.apache.org/xsd/settings-1.0.0.xsd">

	<pluginGroups>
		<pluginGroup>org.apache.tomcat.maven</pluginGroup>
	</pluginGroups>

	<servers>
		<server>
			<id>sovzond-mgis-repository</id>
			<username>maven</username>
			<privateKey>[path to your home directory]/.ssh/id_rsa</privateKey>
			<passphrase>[your passphrase]</passphrase>
		</server>
	</servers>

	<profiles>
		<profile>
			<id>central-profile</id>
			<repositories>
				<repository>
					<id>central</id>
					<name>Central Repository</name>
					<url>http://repo.maven.apache.org/maven2</url>
					<releases><enabled>true</enabled></releases>
					<snapshots><enabled>true</enabled></snapshots>
				</repository>
			</repositories>
		</profile>
		<profile>
			<id>my-profile</id>
			<repositories>
				<repository>
					<id>sovzond-mgis-repository</id>
					<name>My MGIS Repository</name>
					<url>scp://maven@192.168.11.196:/home/maven/.m2/repository</url>
					<layout>default</layout>
					<releases>
						<enabled>true</enabled>
						<updatePolicy>always</updatePolicy>
						<checksumPolicy>warn</checksumPolicy>
					</releases>
					<snapshots>
						<enabled>true</enabled>
						<updatePolicy>never</updatePolicy>
						<checksumPolicy>fail</checksumPolicy>
					</snapshots>
				</repository>
			</repositories>
		</profile>
	</profiles>

	<activeProfiles>
		<activeProfile>central-profile</activeProfile>
		<activeProfile>my-profile</activeProfile>
	</activeProfiles>

</settings>
```


### WEBAPP

1. run cmd

```bash
cd mgis2/mgis-web/src/main/webapp/
npm install -g bower
```


### DB

1. execute sql
```sql
CREATE DATABASE mgis2 ENCODING 'UTF-8' LC_COLLATE 'en_US.UTF-8' LC_CTYPE 'en_US.UTF-8';
CREATE USER mgis2 PASSWORD 'mgis2';
GRANT ALL PRIVILEGES ON DATABASE mgis2 TO mgis2;
ALTER DATABASE mgis2 OWNER TO mgis2;
-- posgis
CREATE EXTENSION postgis;
-- CREATE EXTENSION postgis_sfcgal;
CREATE EXTENSION fuzzystrmatch; --needed for postgis_tiger_geocoder
--optional used by postgis_tiger_geocoder, or can be used standalone
--CREATE EXTENSION address_standardizer;
--CREATE EXTENSION address_standardizer_data_us;
CREATE EXTENSION postgis_tiger_geocoder;
CREATE EXTENSION postgis_topology;
```

2. run cmd, sql
```bash
mgis2/mgis-initial-data/KLADR/convert-all.sh
```

3. run the app
4. run mgis2/mgis-web/src/main/resources/mgis2.bootstrap-data.pgs for mgis2 db to create a user asd/asd
5. run mgis2/mgis-initial-data/OKVED/*.sql, mgis2/mgis-initial-data/*.sql for mgis2 db,
6. run mgis2/mgis-initial-data/KLADR/*.sql for mgis2 db
7. login as asd/asd
8. configure GIS Server as by adding an url : proxy?http://*host*:*port*/geoserver/mgis2/wfs of your future GEO Server instance.

### GEOSERVER

1. download the latest version from http://geoserver.org/release/stable/
2. login as admin/geoserver
3. create workspace mgis2 with namespace http://mgis2.yourcompany.ru/
4. create datastore mgis2 to mgis2 database
5. create sample layers:

* mgis2 	mgis2 	lands_land			EPSG:4326

    table:
```sql
lands_land
```

* mgis2 	lands_land_zone_type_z1 		EPSG:4326

    srid: 94326

    view:
```sql
select l.id, l.geometry
from lands_land l
inner join lands_territorial_zone tz on l.allowedusagebyterritorialzone_id = tz.id
inner join nc_territorial_zone_type zt on tz.zonetype_id = zt.id
where zt.name like 'Зона застройки индивидуальными жилыми домами (Ж1)' and l.geometry is not null
```

* mgis2 	lands_land_zone_type_z3		EPSG:4326

    srid: 94326

    view:
```sql
select l.id, l.geometry
from lands_land l
inner join lands_territorial_zone tz on l.allowedusagebyterritorialzone_id = tz.id
inner join nc_territorial_zone_type zt on tz.zonetype_id = zt.id
where zt.name like 'Зона застройки среднеэтажными жилыми домами (Ж3)' and l.geometry is not null
```
