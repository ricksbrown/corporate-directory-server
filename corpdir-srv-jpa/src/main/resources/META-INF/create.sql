--------
-- Version Details
--------

/** Version Control. */
create table VersionCtrl (
id bigint generated by default as identity,
description varchar(255),
version timestamp,
primary key (id)
);

/** System ctrl. */
create table SystemCtrl (
id bigint generated by default as identity,
description varchar(255),
version timestamp,
versionCtrl_id bigint,
primary key (id)
);

--------
-- Type Tables
--------

/** POSITION TYPE */
create table PositionType (
id bigint generated by default as identity,
active boolean not null,
businessKey varchar(255) not null,
custom boolean not null,
description varchar(255),
version timestamp,
typeLevel integer not null,
primary key (id)
);

/** UNIT-TYPE */
create table UnitType (
id bigint generated by default as identity,
active boolean not null,
businessKey varchar(255) not null,
custom boolean not null,
description varchar(255),
version timestamp,
primary key (id)
);

/** LOCATIONs */
create table Location (
id bigint generated by default as identity,
active boolean not null,
businessKey varchar(255) not null,
custom boolean not null,
description varchar(255),
version timestamp,
country varchar(255),
line1 varchar(255),
line2 varchar(255),
postcode varchar(255),
state varchar(255),
suburb varchar(255),
workStation varchar(255),
parentId bigint,
primary key (id)
);

-----------
-- MAIN Tables
-----------

/** ORG UNIT */
create table OrgUnit (
id bigint generated by default as identity,
active boolean not null,
businessKey varchar(255) not null,
custom boolean not null,
description varchar(255),
version timestamp,
type_id bigint,
primary key (id)
);

/** POSITION */
create table Position (
id bigint generated by default as identity,
active boolean not null,
businessKey varchar(255) not null,
custom boolean not null,
description varchar(255),
version timestamp,
type_id bigint,
primary key (id)
);

/** CONTACT (ie the Person) */
create table Contact (
id bigint generated by default as identity,
active boolean not null,
businessKey varchar(255) not null,
custom boolean not null,
description varchar(255),
version timestamp,
country varchar(255),
line1 varchar(255),
line2 varchar(255),
postcode varchar(255),
state varchar(255),
suburb varchar(255),
workStation varchar(255),
companyTitle varchar(255),
firstName varchar(255),
lastName varchar(255),
image_id bigint,
location_id bigint,
primary key (id)
);

/** IMAGE HOLDER */
create table Image (
id bigint generated by default as identity,
active boolean not null,
custom boolean not null,
description varchar(255),
version timestamp,
image blob,
mimeType varchar(255),
thumbNail blob,
primary key (id)
);

/** CHANNEL (ie email, mobile etc) */
create table Channel (
id bigint generated by default as identity,
active boolean not null,
custom boolean not null,
description varchar(255),
version timestamp,
channelValue varchar(255),
type varchar(255),
contact_id bigint,
primary key (id)
);

------------
-- OrgUnit Version Details
------------

/** OrgUnit versioned links **/
create table OrgUnitLinks (
description varchar(255),
version timestamp,
item_id bigint not null,
versionCtrl_id bigint not null,
parentVersionItem_item_id bigint,
parentVersionItem_versionCtrl_id bigint,
managerPosition_item_id bigint,
managerPosition_versionCtrl_id bigint,
primary key (item_id, versionCtrl_id));

/** The Positions Mangaged by an ORG UNIT */
create table OrgUnitLinks_PositionLinks (
OrgUnitVersionEntity_item_id bigint not null,
OrgUnitVersionEntity_versionCtrl_id bigint not null,
positions_item_id bigint not null,
positions_versionCtrl_id bigint not null,
primary key (OrgUnitVersionEntity_item_id, OrgUnitVersionEntity_versionCtrl_id, positions_item_id, positions_versionCtrl_id)
);


------------
-- Position Version Details
------------

/** Position versioned links **/
create table PositionLinks (
description varchar(255),
version timestamp,
item_id bigint not null,
versionCtrl_id bigint not null,
parentVersionItem_item_id bigint,
parentVersionItem_versionCtrl_id bigint,
orgUnit_item_id bigint,
orgUnit_versionCtrl_id bigint,
primary key (item_id, versionCtrl_id));

/** PositionLinks to ContactLinks. */
create table PositionLinks_ContactLinks (
PositionVersionEntity_item_id bigint not null,
PositionVersionEntity_versionCtrl_id bigint not null,
contacts_item_id bigint not null,
contacts_versionCtrl_id bigint not null,
primary key (PositionVersionEntity_item_id, PositionVersionEntity_versionCtrl_id, contacts_item_id, contacts_versionCtrl_id)
);

create table PositionLinks_OrgUnitLinks (
PositionVersionEntity_item_id bigint not null,
PositionVersionEntity_versionCtrl_id bigint not null,
manageOrgUnits_item_id bigint not null,
manageOrgUnits_versionCtrl_id bigint not null,
primary key (PositionVersionEntity_item_id, PositionVersionEntity_versionCtrl_id, manageOrgUnits_item_id, manageOrgUnits_versionCtrl_id)
);

------------
-- Contact Version Details
------------
/** Contact versioned links **/
create table ContactLinks (
description varchar(255),
version timestamp,
item_id bigint not null,
versionCtrl_id bigint not null,
primary key (item_id, versionCtrl_id)
);

/** ContactLinks to Position*/
create table ContactLinks_PositionLinks (
ContactVersionEntity_item_id bigint not null,
ContactVersionEntity_versionCtrl_id bigint not null,
positions_item_id bigint not null,
positions_versionCtrl_id bigint not null,
primary key (ContactVersionEntity_item_id, ContactVersionEntity_versionCtrl_id, positions_item_id, positions_versionCtrl_id));

-------------
-- Foreign Keys.
-------------

-- System Control Constraints
alter table SystemCtrl add constraint FK_SystemCtrl_to_VersionCtrl foreign key (versionCtrl_id) references VersionCtrl;
-- Limit to one system control record
alter table SystemCtrl add constraint CHECK_SystemCtrl_ID check(id = 1);
-- Location
alter table Location add constraint FK_Location_to_Location foreign key (parentId) references Location;
-- OrgUnit
alter table OrgUnit add constraint FK_OrgUnit_to_UnitType foreign key (type_id) references UnitType;
alter table OrgUnitLinks  add constraint FK_OrgUnitLinks_to_OrgUnit foreign key (item_id) references OrgUnit;
alter table OrgUnitLinks  add constraint FK_OrgUnitLinks_to_VersionCtrl foreign key (versionCtrl_id) references VersionCtrl;
alter table OrgUnitLinks  add constraint FK_OrgUnitLinks_to_OrgUnitLinks foreign key (parentVersionItem_item_id, parentVersionItem_versionCtrl_id) references OrgUnitLinks;
alter table OrgUnitLinks  add constraint FK_OrgUnitLinks_to_PositionLinks foreign key (managerPosition_item_id, managerPosition_versionCtrl_id) references PositionLinks;
alter table OrgUnitLinks_PositionLinks add constraint FK_OrgUnitLinks_PositionLinks_to_PositionLinks foreign key (positions_item_id, positions_versionCtrl_id) references PositionLinks;
alter table OrgUnitLinks_PositionLinks add constraint FK_OrgUnitLinks_PositionLinks_to_OrgUnitLinks foreign key (OrgUnitVersionEntity_item_id, OrgUnitVersionEntity_versionCtrl_id) references OrgUnitLinks;
-- Position
alter table Position add constraint FK_Position_to_PositionType foreign key (type_id) references PositionType;
alter table PositionLinks add constraint FK_PositionLinks_to_Position foreign key (item_id) references Position;
alter table PositionLinks add constraint FK_PositionLinks_to_VersionCtrl foreign key (versionCtrl_id) references VersionCtrl;
alter table PositionLinks add constraint FK_PositionLinks_to_PositionLinks foreign key (parentVersionItem_item_id, parentVersionItem_versionCtrl_id) references PositionLinks;
alter table PositionLinks add constraint FK_PositionLinks_to_OrgUnitlinks foreign key (orgUnit_item_id, orgUnit_versionCtrl_id) references OrgUnitLinks;
alter table PositionLinks_ContactLinks add constraint FK_PositionLinks_ContactLinks_to_ContactLinks foreign key (contacts_item_id, contacts_versionCtrl_id) references ContactLinks;
alter table PositionLinks_ContactLinks add constraint FK_PositionLinks_ContactLinks_to_PositionLinks foreign key (PositionVersionEntity_item_id, PositionVersionEntity_versionCtrl_id) references PositionLinks;
alter table PositionLinks_OrgUnitLinks add constraint FK_PositionLinks_OrgUnitLinks_to_OrgUnitLinks foreign key (manageOrgUnits_item_id, manageOrgUnits_versionCtrl_id) references OrgUnitLinks;
alter table PositionLinks_OrgUnitLinks add constraint FK_PositionLinks_OrgUnitLinks_to_PositionLinks foreign key (PositionVersionEntity_item_id, PositionVersionEntity_versionCtrl_id) references PositionLinks;
-- Contact
alter table Contact add constraint FK_Contact_to_Location foreign key (location_id) references Location;
alter table Contact add constraint FK_Contact_to_Image foreign key (image_id) references Image;
alter table Channel add constraint FK_Channel_to_Contact foreign key (contact_id) references Contact;
alter table ContactLinks  add constraint FK_ContactLinks_to_Contact foreign key (item_id) references Contact;
alter table ContactLinks  add constraint FK_ContactLinks_to_VersionCtrl foreign key (versionCtrl_id) references VersionCtrl;
alter table ContactLinks_PositionLinks add constraint FK_ContactLinks_PositionLinks_to_PositionLinks foreign key (positions_item_id, positions_versionCtrl_id) references PositionLinks;
alter table ContactLinks_PositionLinks add constraint FK_ContactLinks_PositionLinks_to_ContactLinks foreign key (ContactVersionEntity_item_id, ContactVersionEntity_versionCtrl_id) references ContactLinks;

/** Business Keys*/
alter table PositionType add constraint UK_PositionType_Business_Key unique (businessKey);
alter table Position add constraint UK_Position_Business_Key unique (businessKey);
alter table OrgUnit add constraint UK_OrgUnit_Business_Key unique (businessKey);
alter table Location add constraint UK_Location_Business_Key unique (businessKey);
alter table Contact add constraint UK_Contact_Business_Key unique (businessKey);
alter table UnitType add constraint UK_UnitType_Business_Key unique (businessKey);

