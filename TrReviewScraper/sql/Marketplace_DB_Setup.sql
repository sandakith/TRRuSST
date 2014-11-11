drop table APP.MarketItem;
drop table APP.MarketItemReviews;
    
-- \************** DDS.MarketItem *******************************\
create table APP.MarketItem 
   (
	  id					int not null, 
	  recordDate			varchar(100) not null, 
	  ItemId				varchar(100) not null, 
	  ItemName				varchar(100) not null, 
	  ItemDeveloper			varchar(100) not null,
	  ItemDeveloperRating	varchar(100) not null, 	  
	  ItemAvgRating			varchar(50) not null,
	  RatingCount 			varchar(50) not null,
	  LastUpdate 			varchar(50) not null,  
	  NumOfInstalls 		varchar(50) not null,	  
	  ItemPrice  			varchar(50) not null,  
	  ItemSize				varchar(50) not null,
	  ContentRating 		varchar(50) not null,
	  CurrentVersion		varchar(50) not null,
	  fiveStars				varchar(50) not null, 
	  fourStars				varchar(50) not null,
	  threeStarts 			varchar(50) not null,
	  twoStars 				varchar(50) not null,  
	  oneStar 				varchar(50) not null
	  
   );
alter table app.MarketItem add constraint mi_id unique (id);


-- \************** DDS.MarketItemReviews *******************************\
create table APP.MarketItemReviews 
   (
	  id				int not null, 
	  recordDate		varchar(100) not null, 	  
	  reviewItemId		int not null,
	  reviewUserId		varchar(100) not null, 
	  reviewUser		varchar(100) not null, 
	  reviewDate		varchar(100) not null,
	  reviewStarValue	varchar(100) not null,
	  reviewHeading 	varchar(100) not null,
	  reviewBody 		varchar(3000) not null	  
   );

 alter table app.MarketItemReviews add constraint mir_id unique (id, reviewItemId);
