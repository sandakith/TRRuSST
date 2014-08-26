-- Az software review table definition
CREATE TABLE AzSOFTWARE (
  ID INTEGER NOT NULL primary key,
  productId VARCHAR(100) NOT NULL,
  title VARCHAR(100) NOT NULL,
  price VARCHAR(100) NOT NULL,
  userId VARCHAR(100) NOT NULL,
  profileName VARCHAR(100) NOT NULL,
  helpfulness VARCHAR(50) NOT NULL,
  score VARCHAR(50) NOT NULL,
  time VARCHAR(50) NOT NULL,
  summary VARCHAR(50) NOT NULL,
  text VARCHAR(50) NOT NULL
);
