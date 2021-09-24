insert into cities(city_id, ptt, city_name) values(1, 21000, "Novi Sad")
insert into enterprises(enterprise_id, name_enterprise, address, phone, fax,city_id)  values(1, "Proizvodnja DOO", "Bulevar Oslobodjenja 12", "+381624995295", "zzz",1)
insert into enterprises(enterprise_id, name_enterprise, address, phone, fax,city_id)  values(2, "Auto delovi DOO", "Glavna 55", "+381674995888", "zzz",1)
insert into enterprises(enterprise_id, name_enterprise, address, phone, fax,city_id)  values(3, "Soft DOO", "Novosadska 55", "+381674995888", "zzz",1)

insert into partners(partner_id, name, address, phone_number, fax, email,city_id, enterprise_id)  values(1, "partner 1", "Laze Nancica 1", "11111", "111", "partner@gmail.com", 1, 1)
insert into invoices(id, number, date_invoice, date_currency, total_base, total_pdv, total_amount, status, enterprise_id, partner_id, id_year)  values(1, 1,"2020-02-02", "2020-02-02",1,1,1,"waiting?", 1,1, 1)
insert into invoice_items(id, quantity, discount, unit_price, PDVBase, PDVAmount, total_amount, invoice_id, service_id)  values(1, 1,10, 100, 100, 100, 10000, 1, 1)
insert into invoice_items(id, quantity, discount, unit_price, PDVBase, PDVAmount, total_amount, invoice_id, service_id)  values(2, 2,20, 200, 200, 200, 20000, 1, 1)
insert into invoice_items(id, quantity, discount, unit_price, PDVBase, PDVAmount, total_amount, invoice_id, service_id)  values(3, 3,30, 300, 300, 300, 30000, 1, 1)
insert into pdv_categories(id, name)  values(1, "category1")
insert into pdv_rates(pdv_rate_id, percentage, date_of, pdv_category_id)  values(1, 10, "2020-03-03", 1 )

insert into pricelists(pricelist_id, date_from, enterprise_id,percentage, total_price)  values(1, "2020-01-01", 1, 0, 100 )
insert into pricelists(pricelist_id, date_from, enterprise_id,percentage, total_price)  values(2, "2020-03-27", 1 , 0, 0)
insert into pricelists(pricelist_id, date_from, enterprise_id,percentage, total_price)  values(3, "2020-04-10", 1 , 0, 0 )
insert into pricelists(pricelist_id, date_from, enterprise_id,percentage, total_price)  values(4, "2020-03-27", 2, 0, 0 )
insert into pricelists(pricelist_id, date_from, enterprise_id,percentage, total_price)  values(5, "2020-05-30", 3, 0, 0 )

insert into service_groups(id, name, pdv_id, enterprise_id)  values(1,"service group1 ",1,1 )
insert into unit_of_measures(id, name, short_name )  values(1,"kilogram","kg")
insert into servicess(service_id, name, description, goods,groupserv_id,unitmea_id )  values(1,"servis 1","opis servisa 1",true, 1,1 )
insert into price_list_items(price_list_item_id, price, pricelist_id, service_id)  values(1,100,1,1 )

insert into users(user_id, username, password, enterprise_id)values(1, "a", "a",1)

insert into businessyear(id_year, date_from, date_to) values (1, "2021-01-01", "2021-12-31")


