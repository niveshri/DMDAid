CREATE TABLE menus( _id integer primary key autoincrement not null, list_item text , list_type text, next_type text, next text);
CREATE TABLE report_images (_id integer primary key autoincrement not null, image_name text not null, report_id integer not null);
CREATE TABLE reports ( _id integer primary key autoincrement not null, category text not null, report_name text not null, report_date text not null, type text not null, pdfpath text, dirty integer );
CREATE TABLE medication( _id integer primary key autoincrement not null, name text, dose integer, units text, times integer, timesper text, startmonth text, endmonth text, type text, dirty integer );

insert into reports (_id,category,report_name,report_date,type) values (30000,'asd','asd','asd','img');
delete from reports;

insert into medication (_id) values (30000);
delete from medication; 

insert into menus values(null,'Family Member and Primary Care Giver Information','DMDAid','list','Contact Information'); 
insert into menus values(null,'Personal Health Records','DMDAid','list','Personal Health Records'); 
insert into menus values(null,'Current Care Guidelines and Standards','DMDAid','link','Guidelines'); 
insert into menus values(null,'Hospital Stay Log','DMDAid','stay','LOG'); 
insert into menus values(null,'Acute Care Algorithm','DMDAid','link','CAREALGO'); 
insert into menus values(null,'Quality of Life Statement','DMDAid','custompage','QOL');
insert into menus values(null,'Home Care Regimen','DMDAid','care','Homecare');
insert into menus values(null,'Medications','DMDAid','Medications','med');
insert into menus values(null,'OTC Meds','DMDAid','Medications','otc');



insert into menus values(null,'Phone Contacts','Contact Information','Contact','Phone');
insert into menus values(null,'Family Contacts','Contact Information','Add','Phone');


 
insert into menus values(null,'Neurology','Personal Health Records','list','Neurology'); 
insert into menus values(null,'Respiratory/Pulmonology','Personal Health Records','list','Respiratory/Pulmonology'); 
insert into menus values(null,'Gastrointestinal/Nutrition','Personal Health Records','list','Gastrointestinal/Nutrition'); 
insert into menus values(null,'Cardiology','Personal Health Records','list','Cardiology'); 
insert into menus values(null,'Musculoskeletal','Personal Health Records','list','Musculoskeletal');
insert into menus values(null,'Clinical Physician Contact Information','Personal Health Records','addphy','Physician'); 

 
insert into menus values(null,'Other','Personal Health Records','list','Other'); 



insert into menus values(null,'Medical Records','Neurology','report','Neurology Records'); 
insert into menus values(null,'Add Neurologist Contact','Neurology','con','Neurologist Contact'); 



insert into menus values(null,'Medical Records','Respiratory/Pulmonology','report','Respiratory/Pulmonology Records'); 


insert into menus values(null,'Current Pulmonary Functional Test Values','Respiratory/Pulmonology','custompage','Respiratory/Pulmonology'); 
insert into menus values(null,'Add Physicians Contact','Respiratory/Pulmonology','con','Respiratory Contact'); 

insert into menus values(null,'Medical Records','Gastrointestinal/Nutrition','report','Gastrointestinal/Nutrition Records'); 
insert into menus values(null,'Add Physicians Contact','Gastrointestinal/Nutrition','con','Physician Contact'); 

insert into menus values(null,'Medical Records','Cardiology','report','Cardiology Records'); 
insert into menus values(null,'Add Physicians Contact','Cardiology','con','Physician Contact'); 

insert into menus values(null,'Most recent Echo Report: SV, EF, SF','Cardiology','custompage','Cardiology');




insert into menus values(null,'Medical Records','Musculoskeletal','report','Musculoskeletal Records'); 
insert into menus values(null,'Add Physicians Contact','Musculoskeletal','con','Physician Contact'); 


insert into menus values(null,'Wheelchair description','Musculoskeletal','custompage','Musculoskeletal'); 


insert into menus values(null,'Medical Reports','Other','report','Other'); 