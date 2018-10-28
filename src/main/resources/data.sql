
insert into PET
  (id, name, species)
  values
  (1000,'Fido', 'DOG'),
  (1001,'Sylvester', 'CAT'),
  (1002,'Tweety', 'BIRD')
  ;

insert into OWNER
  (id, name)
  values
  (2000,'Bill Hader'),
  (2001,'Jane Curtain'),
  ;

insert into OWNER_PETS
  (owner_id, pets_id)
  values
  (2000, 1000),
  (2000, 1001),
  (2001, 1002),
  ;

insert into VET
  (id, name)
  values
  (4000,'Greg Isakov'),
  (4001,'Thom Yorke'),
  ;

insert into APPOINTMENT
  (id, time, pet_id, vet_id)
  values
  (3000, '2018-10-28 23:59:59 UTC', 1000, 4000)
  ;


