create table school (
  id INT not null primary key,
  name VARCHAR(100)
);

create table pupil (
  id INT not null primary key,
  name VARCHAR(50) not null,
  school_id INT not null,
  foreign key (school_id) references school(id)
);
