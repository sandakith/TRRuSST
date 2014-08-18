select REVIEWITEMID, count(REVIEWUSER) from APP.MARKETITEMREVIEWS ;

select REVIEWITEMID, count(REVIEWUSER) from APP.MARKETITEMREVIEWS group by REVIEWITEMID;
	
-- select max(id)id from APP.MARKETITEM;

-- select max(id)id from app.marketitemreviews;

-- select max(id)id from app.marketitemreviews;

-- select count(ITEMID)  from APP.MARKETITEM ;

-- select ID, ITEMID  from APP.MARKETITEM where ITEMID like '%weather%';

select REVIEWHEADING, REVIEWBODY  from APP.MARKETITEMREVIEWS where REVIEWITEMID = 6279, 6280