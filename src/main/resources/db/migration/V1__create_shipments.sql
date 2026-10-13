CREATE TABLE shipments (
 id BIGSERIAL PRIMARY KEY, tracking_number VARCHAR(32) NOT NULL UNIQUE,
 sender_name VARCHAR(120) NOT NULL, recipient_name VARCHAR(120) NOT NULL,
 origin VARCHAR(160) NOT NULL, destination VARCHAR(160) NOT NULL,
 weight_kg NUMERIC(9,3) NOT NULL CHECK(weight_kg > 0), price NUMERIC(12,2) NOT NULL CHECK(price >= 0),
 status VARCHAR(32) NOT NULL, created_at TIMESTAMPTZ NOT NULL, updated_at TIMESTAMPTZ NOT NULL
);
CREATE INDEX idx_shipments_status ON shipments(status);
CREATE INDEX idx_shipments_created_at ON shipments(created_at DESC);
