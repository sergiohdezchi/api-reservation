-- PostgreSQL version of MySQL dump

DROP TABLE IF EXISTS itinerary CASCADE;
DROP TABLE IF EXISTS passenger CASCADE;
DROP TABLE IF EXISTS price CASCADE;
DROP TABLE IF EXISTS reservation CASCADE;
DROP TABLE IF EXISTS segment CASCADE;

-- Table: price
CREATE TABLE price (
  id BIGSERIAL PRIMARY KEY,
  base_price NUMERIC(38,2) NOT NULL,
  total_price NUMERIC(38,2) NOT NULL,
  total_tax NUMERIC(38,2) NOT NULL,
  version BIGINT
);

-- Table: itinerary
CREATE TABLE itinerary (
  id BIGSERIAL PRIMARY KEY,
  version BIGINT,
  itinerary_id BIGINT,
  CONSTRAINT uk_itinerary_id UNIQUE (itinerary_id),
  CONSTRAINT fk_itinerary_price FOREIGN KEY (itinerary_id) REFERENCES price(id)
);

-- Table: reservation
CREATE TABLE reservation (
  id BIGSERIAL PRIMARY KEY,
  creation_date DATE NOT NULL,
  version BIGINT,
  itinerary_id BIGINT,
  CONSTRAINT fk_reservation_itinerary FOREIGN KEY (itinerary_id) REFERENCES itinerary(id)
);

-- Table: passenger
CREATE TABLE passenger (
  id BIGSERIAL PRIMARY KEY,
  birthday DATE NOT NULL,
  version BIGINT,
  reservation_id BIGINT,
  first_name VARCHAR(30) NOT NULL,
  last_name VARCHAR(30) NOT NULL,
  document_number VARCHAR(255) NOT NULL,
  document_type VARCHAR(255) NOT NULL,
  CONSTRAINT fk_passenger_reservation FOREIGN KEY (reservation_id) REFERENCES reservation(id)
);

-- Table: segment
CREATE TABLE segment (
  id BIGSERIAL PRIMARY KEY,
  carrier VARCHAR(3) NOT NULL,
  destination VARCHAR(3) NOT NULL,
  origin VARCHAR(3) NOT NULL,
  version BIGINT,
  itinerary_id BIGINT,
  arrival VARCHAR(255) NOT NULL,
  departure VARCHAR(255) NOT NULL,
  CONSTRAINT fk_segment_itinerary FOREIGN KEY (itinerary_id) REFERENCES itinerary(id)
);