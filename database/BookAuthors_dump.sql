--
-- PostgreSQL database dump
--

-- Dumped from database version 16.0
-- Dumped by pg_dump version 16.0

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
-- Name: author; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.author (
    author_id integer NOT NULL,
    first_name character varying(50),
    last_name character varying(50),
    birth_date date,
    death_date date,
    children_count integer
);


ALTER TABLE public.author OWNER TO postgres;

--
-- Name: author_author_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.author_author_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.author_author_id_seq OWNER TO postgres;

--
-- Name: author_author_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.author_author_id_seq OWNED BY public.author.author_id;


--
-- Name: bibliography; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bibliography (
    author_id integer,
    book_id integer
);


ALTER TABLE public.bibliography OWNER TO postgres;

--
-- Name: book; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.book (
    book_id integer NOT NULL,
    book_name character varying(100),
    release_year integer
);


ALTER TABLE public.book OWNER TO postgres;

--
-- Name: book_book_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.book_book_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.book_book_id_seq OWNER TO postgres;

--
-- Name: book_book_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.book_book_id_seq OWNED BY public.book.book_id;


--
-- Name: country; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.country (
    country_id integer NOT NULL,
    country_name character varying(50)
);


ALTER TABLE public.country OWNER TO postgres;

--
-- Name: country_country_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.country_country_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.country_country_id_seq OWNER TO postgres;

--
-- Name: country_country_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.country_country_id_seq OWNED BY public.country.country_id;


--
-- Name: education; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.education (
    author_id integer,
    university_id integer
);


ALTER TABLE public.education OWNER TO postgres;

--
-- Name: university; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.university (
    university_id integer NOT NULL,
    university_name character varying(50),
    country_id integer
);


ALTER TABLE public.university OWNER TO postgres;

--
-- Name: university_university_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.university_university_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.university_university_id_seq OWNER TO postgres;

--
-- Name: university_university_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.university_university_id_seq OWNED BY public.university.university_id;


--
-- Name: author author_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.author ALTER COLUMN author_id SET DEFAULT nextval('public.author_author_id_seq'::regclass);


--
-- Name: book book_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book ALTER COLUMN book_id SET DEFAULT nextval('public.book_book_id_seq'::regclass);


--
-- Name: country country_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.country ALTER COLUMN country_id SET DEFAULT nextval('public.country_country_id_seq'::regclass);


--
-- Name: university university_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.university ALTER COLUMN university_id SET DEFAULT nextval('public.university_university_id_seq'::regclass);


--
-- Data for Name: author; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.author (author_id, first_name, last_name, birth_date, death_date, children_count) FROM stdin;
1	George	Orwell	1903-06-25	1903-06-25	1
2	Ernest	Hemingway	1899-07-21	1961-07-02	3
3	Franz	Kafka	1883-07-03	1924-06-03	0
4	Fyodor	Dostoevsky	1821-11-11	1881-02-09	4
5	Agatha	Christie	1890-09-15	1976-01-12	1
6	Cormac	McCarthy	1933-07-20	2023-10-13	1
7	Leo	Tolstoy	1828-09-09	1910-11-20	13
8	Charles	Darwin	1809-02-12	1882-04-19	10
9	Napoleon	Hill	1883-10-26	1970-11-08	3
10	Aldous	Huxley	1894-07-26	1963-11-22	1
\.


--
-- Data for Name: bibliography; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.bibliography (author_id, book_id) FROM stdin;
1	1
1	2
2	3
2	4
3	5
3	6
3	7
4	8
4	9
6	12
6	13
5	10
5	11
7	15
7	16
8	17
8	18
9	19
9	20
9	21
10	22
10	23
\.


--
-- Data for Name: book; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.book (book_id, book_name, release_year) FROM stdin;
1	1984	1949
2	Animal Farm	1945
3	The Old Man and the Sea	1952
4	A Farewell to Arms	1929
5	The Metamorphosis	1915
6	The Trial	1925
7	The Castle	1926
8	Crime and Punishment	1866
9	The Brothers Karamazov	1880
10	Murder on the Orient Express	1934
11	And Then There Were None	1939
13	No Country for Old Men	2005
12	Blood Meridian	1985
15	War and Peace	1865
16	Anna Karenina	1875
17	On the Origin of Species	1859
18	The Descent of Man	1871
19	Think and Grow Rich	1937
20	The Law of Success	1925
21	The Master Key to Riches	1945
22	Brave New World	1932
23	The Doors of Perception	1954
\.


--
-- Data for Name: country; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.country (country_id, country_name) FROM stdin;
1	United Kingdom
2	Czech Republic
3	Russia
4	United States
\.


--
-- Data for Name: education; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.education (author_id, university_id) FROM stdin;
1	1
3	2
4	3
6	4
7	5
8	6
9	7
10	8
\.


--
-- Data for Name: university; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.university (university_id, university_name, country_id) FROM stdin;
2	Charles University	2
1	Eton College	1
3	Academy of Military Engineering	3
4	University of Tennessee	4
5	Kazan University	3
6	Christs College	1
7	Georgetown University	4
8	Balliol College	1
\.


--
-- Name: author_author_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.author_author_id_seq', 1, true);


--
-- Name: book_book_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.book_book_id_seq', 23, true);


--
-- Name: country_country_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.country_country_id_seq', 4, true);


--
-- Name: university_university_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.university_university_id_seq', 8, true);


--
-- Name: author author_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.author
    ADD CONSTRAINT author_pkey PRIMARY KEY (author_id);


--
-- Name: book book_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT book_pkey PRIMARY KEY (book_id);


--
-- Name: country country_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.country
    ADD CONSTRAINT country_pkey PRIMARY KEY (country_id);


--
-- Name: university university_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.university
    ADD CONSTRAINT university_pkey PRIMARY KEY (university_id);


--
-- Name: bibliography discography_author_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bibliography
    ADD CONSTRAINT discography_author_id_fkey FOREIGN KEY (author_id) REFERENCES public.author(author_id);


--
-- Name: bibliography discography_book_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bibliography
    ADD CONSTRAINT discography_book_id_fkey FOREIGN KEY (book_id) REFERENCES public.book(book_id);


--
-- Name: education education_author_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.education
    ADD CONSTRAINT education_author_id_fkey FOREIGN KEY (author_id) REFERENCES public.author(author_id);


--
-- Name: education education_university_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.education
    ADD CONSTRAINT education_university_id_fkey FOREIGN KEY (university_id) REFERENCES public.university(university_id);


--
-- Name: university university_country_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.university
    ADD CONSTRAINT university_country_id_fkey FOREIGN KEY (country_id) REFERENCES public.country(country_id);


--
-- PostgreSQL database dump complete
--

