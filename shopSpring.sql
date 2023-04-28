--
-- PostgreSQL database dump
--

-- Dumped from database version 15.1
-- Dumped by pg_dump version 15.0

-- Started on 2023-04-28 16:22:01 CEST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 32886)
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    description character varying(500),
    tax numeric(6,2) NOT NULL
);


ALTER TABLE public.category OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 32885)
-- Name: category_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.category_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.category_id_seq OWNER TO postgres;

--
-- TOC entry 3662 (class 0 OID 0)
-- Dependencies: 214
-- Name: category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.category_id_seq OWNED BY public.category.id;


--
-- TOC entry 221 (class 1259 OID 32936)
-- Name: item_in_order; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.item_in_order (
    order_id integer NOT NULL,
    item_id integer NOT NULL
);


ALTER TABLE public.item_in_order OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 32902)
-- Name: items; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.items (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    description character varying(500) NOT NULL,
    price numeric(16,2) NOT NULL,
    category_id integer NOT NULL
);


ALTER TABLE public.items OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 32901)
-- Name: items_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.items_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.items_id_seq OWNER TO postgres;

--
-- TOC entry 3663 (class 0 OID 0)
-- Dependencies: 217
-- Name: items_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.items_id_seq OWNED BY public.items.id;


--
-- TOC entry 220 (class 1259 OID 32924)
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    id integer NOT NULL,
    status character varying(30) DEFAULT 'open'::character varying NOT NULL,
    user_id character varying(100) NOT NULL
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 32923)
-- Name: orders_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.orders_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orders_id_seq OWNER TO postgres;

--
-- TOC entry 3664 (class 0 OID 0)
-- Dependencies: 219
-- Name: orders_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.orders_id_seq OWNED BY public.orders.id;


--
-- TOC entry 222 (class 1259 OID 32981)
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles (
    user_id character varying(100) NOT NULL,
    role character varying(50) NOT NULL
);


ALTER TABLE public.roles OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 32894)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id character varying(100) NOT NULL,
    display_name character varying(100) NOT NULL,
    address character varying(500) NOT NULL,
    phone character varying(100),
    password character varying(200) NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 3483 (class 2604 OID 32889)
-- Name: category id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category ALTER COLUMN id SET DEFAULT nextval('public.category_id_seq'::regclass);


--
-- TOC entry 3484 (class 2604 OID 32905)
-- Name: items id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.items ALTER COLUMN id SET DEFAULT nextval('public.items_id_seq'::regclass);


--
-- TOC entry 3485 (class 2604 OID 32927)
-- Name: orders id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders ALTER COLUMN id SET DEFAULT nextval('public.orders_id_seq'::regclass);


--
-- TOC entry 3649 (class 0 OID 32886)
-- Dependencies: 215
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.category (id, name, description, tax) FROM stdin;
1	Pants	We love pants in all different styles & cuts. Discover \n\t\t\t\t\t\t\t\t\t\t\t\t\t\t  well-fitted, on-trend pants. We got your fashion essentials! Free shipping.	22.00
3	Coat	Shop the latest trends within womens clothes and shoes from over 350 brands online. Free shipping.	22.00
5	Jeans	Keep your wardrobe updated with the seasons latest styles with our extensive new season collection.! Free shipping.	22.00
6	Carss	string	12.00
\.


--
-- TOC entry 3655 (class 0 OID 32936)
-- Dependencies: 221
-- Data for Name: item_in_order; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.item_in_order (order_id, item_id) FROM stdin;
3	48
30	25
30	67
8	21
63	48
64	48
65	48
14	55
23	92
60	79
71	48
45	96
26	85
22	36
28	23
48	66
55	14
29	18
60	97
15	11
15	12
60	24
58	47
6	17
31	32
39	68
37	41
11	29
44	41
46	92
10	38
10	2
48	48
55	92
58	19
23	66
60	63
9	17
47	78
33	56
35	19
59	66
19	66
8	29
\.


--
-- TOC entry 3652 (class 0 OID 32902)
-- Dependencies: 218
-- Data for Name: items; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.items (id, name, description, price, category_id) FROM stdin;
2	Australian Gold	Barbet, black-collared	770.00	3
3	CVS Pharmacy Sport SPF 50	Japanese macaque	535.00	5
11	Biscolax	Deer, swamp	605.00	3
14	Warm Sap Patch	Tortoise, asian foreset	92.00	3
15	Arnica e pl. tota 30	Lory, rainbow	886.00	3
17	Amiodarone Hydrochloride	Savanna fox	633.00	3
18	Mineral Oil	Indian tree pie	313.00	1
19	ibuprofen	Anaconda (unidentified)	471.00	3
20	Fexofenadine Hydrochloride	Colobus, black and white	860.00	3
21	Russian Olive Pollen	Crested screamer	937.00	1
23	Ice Quake	Ant (unidentified)	842.00	1
24	Tobramycin	Oriental white-backed vulture	987.00	1
25	Allopurinol	Penguin, magellanic	75.00	5
26	Dopamine	Pademelon, red-legged	939.00	3
28	Methocarbamol	Hen, sage	184.00	5
29	Magnesium Citrate	Nuthatch, red-breasted	624.00	3
32	Prednisolone	Wallaby, dama	294.00	5
33	Antiac Acne Clearing	Grebe, little	704.00	5
36	athletes foot	Lion, mountain	181.00	5
38	TRIPLE PASTE-AF	Whale, long-finned pilot	478.00	5
41	ESIKA	Emu	576.00	3
42	NUCYNTA	California sea lion	176.00	1
46	DermOtic	Vulture, griffon	701.00	3
47	Meaningful Beauty Cindy Crawford	African elephant	90.00	1
48	DEMEROL	Stork, marabou	678.00	1
49	albuterol sulfate	Brindled gnu	390.00	5
50	Clonazepam	Richardson's ground squirrel	447.00	1
55	Bioelements	Roadrunner, greater	154.00	1
56	Flawless Finish Dual Perfection Makeup SPF 8 Sunbeige	White-faced tree rat	468.00	5
58	ERYTHROMYCIN	Red-capped cardinal	476.00	3
61	Oil-Free Foaming Acne Wash	Lynx, african	93.00	3
63	Original Antiseptic	Pied avocet	546.00	3
66	Lice Killing	Indian giant squirrel	857.00	1
67	levofloxacin	Snake, racer	294.00	3
68	night relief	Agile wallaby	717.00	1
69	Actos	Stork, greater adjutant	970.00	1
73	health mart cold and allergy	Mountain lion	505.00	5
74	Enalapril maleate and hydrochlorothiazide	Lourie, grey	754.00	5
77	THERA Moisturizing Body Shield	Common langur	489.00	5
78	Dynashield	Frilled dragon	560.00	1
79	Phenazopyridine HCl	Manatee	81.00	3
80	AMOXICILLIN	Wolf, mexican	937.00	5
82	Whole Egg	Wood pigeon	899.00	1
83	Simvastatin	Fox, asian red	521.00	5
85	Crayola Wild Blue Yonder Roll On Hand Sanitizer	North American porcupine	593.00	3
86	Mupirocin	Eurasian red squirrel	581.00	5
88	Ibuprofen	Prairie falcon	157.00	5
91	Locoid	Rufous tree pie	371.00	3
92	Cuprum aceticum Nicotiana	Snowy egret	63.00	3
94	Pramipexole Dihydrochloride	Magistrate black colobus	936.00	5
95	Aspirin	Paca	253.00	5
96	CLONIDINE HYDROCHLORIDE	Kafue flats lechwe	434.00	5
97	CARBON DIOXIDE	Vulture, bengal	226.00	1
100	Bio Pine	Civet, small-toothed palm	849.00	3
12	Minerral Wear Talc Free Airbrushing Pressed Powder	Rhinoceros, square-lipped	0.00	1
\.


--
-- TOC entry 3654 (class 0 OID 32924)
-- Dependencies: 220
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.orders (id, status, user_id) FROM stdin;
4	closed	tfortuneg@canalblog.com
6	closed	dduffielda@buzzfeed.com
8	in progress	amouldere@wikipedia.org
9	open	dduffielda@buzzfeed.com
10	open	pgreensmithb@state.tx.us
11	closed	tfortuneg@canalblog.com
13	open	bhayne9@bing.com
14	closed	skaasmann3@ed.gov
16	open	pkrzyzaniakh@mysql.com
17	in progress	csergean6@timesonline.co.uk
18	open	skaasmann3@ed.gov
19	open	bhayne9@bing.com
22	closed	skaasmann3@ed.gov
23	in progress	pgreensmithb@state.tx.us
25	open	anapolitano2@miitbeian.gov.cn
26	closed	skaasmann3@ed.gov
27	in progress	rascroft7@booking.com
28	open	jdunbletonf@epa.gov
29	closed	jdunbletonf@epa.gov
30	open	ekilbournc@ycombinator.com
31	open	rascroft7@booking.com
33	in progress	pkrzyzaniakh@mysql.com
34	closed	rascroft7@booking.com
35	in progress	jdunbletonf@epa.gov
36	open	pgreensmithb@state.tx.us
37	open	abaxter8@mashable.com
39	closed	tfortuneg@canalblog.com
40	open	tfortuneg@canalblog.com
42	closed	anapolitano2@miitbeian.gov.cn
44	open	amouldere@wikipedia.org
45	closed	pkrzyzaniakh@mysql.com
46	open	anapolitano2@miitbeian.gov.cn
47	open	amouldere@wikipedia.org
48	open	adeeneyi@ca.gov
49	open	pkrzyzaniakh@mysql.com
51	open	skaasmann3@ed.gov
54	closed	rascroft7@booking.com
55	open	dduffielda@buzzfeed.com
56	open	amouldere@wikipedia.org
58	closed	jdunbletonf@epa.gov
59	in progress	abaxter8@mashable.com
60	in progress	jdunbletonf@epa.gov
3	close	csergean6@timesonline.co.uk
63	close	csergean6@timesonline.co.uk
64	open	csergean6@timesonline.co.uk
65	open	csergean6@timesonline.co.uk
69	in progress	csergean6@timesonline.co.uk
70	in progress	csergean6@timesonline.co.uk
71	open	csergean6@timesonline.co.uk
72	open	csergean6@timesonline.co.uk
73	in progress	csergean6@timesonline.co.uk
74	open	csergean6@timesonline.co.uk
75	open	csergean6@timesonline.co.uk
15	open	csergean6@timesonline.co.uk
\.


--
-- TOC entry 3656 (class 0 OID 32981)
-- Dependencies: 222
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.roles (user_id, role) FROM stdin;
anapolitano2@miitbeian.gov.cn	ADMIN
\.


--
-- TOC entry 3650 (class 0 OID 32894)
-- Dependencies: 216
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, display_name, address, phone, password) FROM stdin;
anapolitano2@miitbeian.gov.cn	Avivah Napolitano	0276 Summer Ridge Place 23	427-972-3714	{MD5}19ab450c8a42a6ec985ac27039c14887
egummory5@ow.ly	Eydie Gummory	86 Longview Lane	774-153-4317	{MD5}75c7d8f3f6f5802c7fae5bf899485d9a
csergean6@timesonline.co.uk	Carlin Sergean	5376 Kipling Plaza 45	333-150-7243	{MD5}24f5fa122f0fc989209886ae2d119485
rascroft7@booking.com	Robyn Ascroft	84453 Derek Avenue 56	176-353-4996	{MD5}f8bbc336a0f7f2a8220101d29bddbe4c
abaxter8@mashable.com	Arlan Baxter	65 Forest Dale Alley 67	204-391-0500	{MD5}550830d3671e40bbba62b9d8e79b52a0
bhayne9@bing.com	Bathsheba Hayne	928 Corscot Court 2222	964-144-7870	{MD5}4cf0bf68f9fc8af7dd95f77bdc541d1b
pgreensmithb@state.tx.us	Patience Greensmith	5893 Ludington Court 7	632-510-8899	{MD5}127438a22c47bdd07e719a004b998648
ekilbournc@ycombinator.com	Elmira Kilbourn	942 Dwight Park 78	629-702-8979	{MD5}acbb5975f49eb9b05cbd11e0080d7407
amouldere@wikipedia.org	Adan Moulder	49414 Becker Junction 78	344-215-8539	{MD5}9445b865ebd4d8c8b54f01bedcedff50
jdunbletonf@epa.gov	Josephina Dunbleton	97367 Glendale Parkway 78	563-419-8504	{MD5}6211bdb1824ad390e4103cc90127dea1
tfortuneg@canalblog.com	Tremaine Fortune	8 Carey Park 43	687-352-0954	{MD5}aa53f9fca6c05679316cc529b0d5478c
pkrzyzaniakh@mysql.com	Pooh Krzyzaniak	63200 Chive Park 56	898-424-7564	{MD5}5645915f32751a98c142bac17635e5b0
adeeneyi@ca.gov	Audry Deeney	62 Clove Alley 67	142-976-9544	{MD5}167babcd4eba10f84503e2bb3b5b5ed7
anapoliftano2@miitbeian.gov.cn	Avivah Napolitano	0276 Summer Ridge Place 23	427-972-3714	{bcrypt}$2a$10$/iKSmivjnKW3H3eekkyocO0uJtWHQ8/urqtE7dDxa.Wpod0sJzv/q
skaasmann3@ed.gov	Samantha Kaasmann	8081 Grayhawk Road 6	150-861-0327	{bcrypt}$2a$10$zERifi4UrS2c8KJhWO1wa.Me7tsrOhfaZ8ZfWi1pfq.z9XF00a.sG
dduffielda@buzzfeed.com	Dania Duffield	209 Hermina Way 5	826-367-870080	{bcrypt}$2a$10$.1tExOoenrM72lTQsCV7xOKUUr61vub0SdcMewYWmoqHxCfWoMWSW
\.


--
-- TOC entry 3665 (class 0 OID 0)
-- Dependencies: 214
-- Name: category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.category_id_seq', 1, true);


--
-- TOC entry 3666 (class 0 OID 0)
-- Dependencies: 217
-- Name: items_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.items_id_seq', 1, false);


--
-- TOC entry 3667 (class 0 OID 0)
-- Dependencies: 219
-- Name: orders_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orders_id_seq', 79, true);


--
-- TOC entry 3488 (class 2606 OID 32893)
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- TOC entry 3498 (class 2606 OID 32940)
-- Name: item_in_order item_in_order_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_in_order
    ADD CONSTRAINT item_in_order_pkey PRIMARY KEY (order_id, item_id);


--
-- TOC entry 3492 (class 2606 OID 33012)
-- Name: items items_name_ukey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.items
    ADD CONSTRAINT items_name_ukey UNIQUE (name);


--
-- TOC entry 3494 (class 2606 OID 32909)
-- Name: items items_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.items
    ADD CONSTRAINT items_pkey PRIMARY KEY (id);


--
-- TOC entry 3496 (class 2606 OID 32930)
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);


--
-- TOC entry 3500 (class 2606 OID 32985)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (user_id, role);


--
-- TOC entry 3490 (class 2606 OID 32900)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 3503 (class 2606 OID 33006)
-- Name: item_in_order item_in_order_item_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_in_order
    ADD CONSTRAINT item_in_order_item_id_fkey FOREIGN KEY (item_id) REFERENCES public.items(id) ON DELETE RESTRICT;


--
-- TOC entry 3504 (class 2606 OID 32941)
-- Name: item_in_order item_in_order_order_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_in_order
    ADD CONSTRAINT item_in_order_order_id_fkey FOREIGN KEY (order_id) REFERENCES public.orders(id) ON DELETE CASCADE;


--
-- TOC entry 3501 (class 2606 OID 33001)
-- Name: items items_category_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.items
    ADD CONSTRAINT items_category_id_fkey FOREIGN KEY (category_id) REFERENCES public.category(id) ON DELETE CASCADE;


--
-- TOC entry 3502 (class 2606 OID 32991)
-- Name: orders orders_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE;


--
-- TOC entry 3505 (class 2606 OID 32986)
-- Name: roles roles_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE;


-- Completed on 2023-04-28 16:22:01 CEST

--
-- PostgreSQL database dump complete
--

