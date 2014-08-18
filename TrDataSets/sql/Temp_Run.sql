

CREATE TABLE MARKETITEM (
		ID INTEGER NOT NULL,
		RECORDDATE VARCHAR(100) NOT NULL,
		ITEMID VARCHAR(100) NOT NULL,
		ITEMNAME VARCHAR(100) NOT NULL,
		ITEMDEVELOPER VARCHAR(100) NOT NULL,
		ITEMDEVELOPERRATING VARCHAR(100) NOT NULL,
		ITEMAVGRATING VARCHAR(50) NOT NULL,
		RATINGCOUNT VARCHAR(50) NOT NULL,
		LASTUPDATE VARCHAR(50) NOT NULL,
		NUMOFINSTALLS VARCHAR(50) NOT NULL,
		ITEMPRICE VARCHAR(50) NOT NULL,
		ITEMSIZE VARCHAR(50) NOT NULL,
		CONTENTRATING VARCHAR(50) NOT NULL,
		CURRENTVERSION VARCHAR(50) NOT NULL,
		FIVESTARS VARCHAR(50) NOT NULL,
		FOURSTARS VARCHAR(50) NOT NULL,
		THREESTARTS VARCHAR(50) NOT NULL,
		TWOSTARS VARCHAR(50) NOT NULL,
		ONESTAR VARCHAR(50) NOT NULL
	);



CREATE TABLE MARKETITEMREVIEWS (
		ID INTEGER NOT NULL,
		RECORDDATE VARCHAR(100) NOT NULL,
		REVIEWITEMID INTEGER NOT NULL,
		REVIEWUSER VARCHAR(100) NOT NULL,
		REVIEWDATE VARCHAR(100) NOT NULL,
		REVIEWPHONE VARCHAR(100) NOT NULL,
		REVIEWSTARVALUE VARCHAR(100) NOT NULL,
		REVIEWHEADING VARCHAR(100) NOT NULL,
		REVIEWBODY VARCHAR(3000) NOT NULL
	);
