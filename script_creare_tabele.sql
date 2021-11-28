CREATE TABLE clienti (
    id_client    NUMBER(7) NOT NULL,
    nume_client  VARCHAR2(40) NOT NULL,
    telefon      VARCHAR2(10) NOT NULL,
    email        VARCHAR2(40)
)
LOGGING;

ALTER TABLE clienti
    ADD CONSTRAINT clienti_id_client_ck CHECK ( id_client BETWEEN 1 AND 9999999 );

ALTER TABLE clienti
    ADD CONSTRAINT clienti_nume_client_ck CHECK ( NOT REGEXP_LIKE ( nume_client,
                                                                    '[[:digit:]]' )
                                                      AND length(nume_client) <= 40
                                                      AND length(nume_client) >= 1 );

ALTER TABLE clienti
    ADD CONSTRAINT clienti_telefon_ck CHECK ( length(telefon) = 10
                                              AND substr(telefon, 1, 1) = '0'
                                              AND ( substr(telefon, 2, 1) = '2'
                                                    OR substr(telefon, 2, 1) = '3'
                                                    OR substr(telefon, 2, 1) = '7' ) );

ALTER TABLE clienti
    ADD CONSTRAINT clienti_email_ck CHECK ( REGEXP_LIKE ( email,
                                                          '[a-z0-9._%-]+@[a-z0-9._%-]+\.[a-z]{2,4}' )
                                            AND length(email) <= 40 );

ALTER TABLE clienti ADD CONSTRAINT clienti_pk PRIMARY KEY ( id_client );

ALTER TABLE clienti ADD CONSTRAINT clienti_telefon_uk UNIQUE ( telefon );

ALTER TABLE clienti ADD CONSTRAINT clienti_email_uk UNIQUE ( email );

CREATE TABLE detalii_posturi (
    id_post    NUMBER(3) NOT NULL,
    frecventa  NUMBER(8, 3) NOT NULL,
    canal      NUMBER(3) NOT NULL
)
LOGGING;

ALTER TABLE detalii_posturi
    ADD CONSTRAINT detalii_posturi_id_post_ck CHECK ( id_post BETWEEN 1 AND 999 );

ALTER TABLE detalii_posturi ADD CONSTRAINT detalii_posturi_frecventa_ck CHECK ( frecventa > 0 );

ALTER TABLE detalii_posturi
    ADD CONSTRAINT detalii_posturi_canal_ck CHECK ( canal BETWEEN 1 AND 999 );

CREATE UNIQUE INDEX detalii_posturi__idx ON
    detalii_posturi (
        id_post
    ASC )
        LOGGING;

ALTER TABLE detalii_posturi ADD CONSTRAINT detalii_posturi_pk PRIMARY KEY ( id_post );

ALTER TABLE detalii_posturi ADD CONSTRAINT denumire_posturi_frecventa_uk UNIQUE ( frecventa );

ALTER TABLE detalii_posturi ADD CONSTRAINT denumire_posturi_canal_uk UNIQUE ( canal );

CREATE TABLE pachete (
    id_pachet        NUMBER(2) NOT NULL,
    denumire_pachet  VARCHAR2(25) NOT NULL,
    data_start       DATE NOT NULL,
    data_end         DATE,
    pret             NUMBER(5, 2) NOT NULL
)
LOGGING;

ALTER TABLE pachete
    ADD CONSTRAINT pachete_id_pachet_ck CHECK ( id_pachet BETWEEN 1 AND 99 );

ALTER TABLE pachete
    ADD CONSTRAINT pachete_denumire_pachet_ck CHECK ( length(denumire_pachet) <= 25
                                                      AND length(denumire_pachet) > 1 );

ALTER TABLE pachete
    ADD CONSTRAINT pachete_pret_ck CHECK ( pret BETWEEN 12 AND 200 );

ALTER TABLE pachete ADD CONSTRAINT pachete_pk PRIMARY KEY ( id_pachet );

ALTER TABLE pachete ADD CONSTRAINT pachete_denumire_pachete_uk UNIQUE ( denumire_pachet );

CREATE TABLE pachete_posturi (
    id_post    NUMBER(3) NOT NULL,
    id_pachet  NUMBER(2) NOT NULL
)
LOGGING;

ALTER TABLE pachete_posturi ADD CONSTRAINT pachete_posturi_pk PRIMARY KEY ( id_post,
                                                                            id_pachet );

CREATE TABLE posturi (
    id_post        NUMBER(3) NOT NULL,
    denumire_post  VARCHAR2(25) NOT NULL,
    data_start     DATE NOT NULL,
    data_end       DATE,
    tip            VARCHAR2(2) NOT NULL
)
LOGGING;

ALTER TABLE posturi
    ADD CONSTRAINT posturi_id_post_ck CHECK ( id_post BETWEEN 1 AND 999 );

ALTER TABLE posturi
    ADD CONSTRAINT posturi_denumire_post_ck CHECK ( length(denumire_post) <= 25
                                                    AND length(denumire_post) > 1 );

ALTER TABLE posturi
    ADD CONSTRAINT posturi_tip_ck CHECK ( tip IN ( '4K', 'A', 'D', 'HD' ) );

ALTER TABLE posturi ADD CONSTRAINT posturi_pk PRIMARY KEY ( id_post );

ALTER TABLE posturi ADD CONSTRAINT posturi_denumire_post_uk UNIQUE ( denumire_post );

ALTER TABLE pachete_posturi
    ADD CONSTRAINT pachete_posturi_pachete_fk FOREIGN KEY ( id_pachet )
        REFERENCES pachete ( id_pachet )
    NOT DEFERRABLE;

ALTER TABLE pachete_posturi
    ADD CONSTRAINT pachete_posturi_posturi_fk FOREIGN KEY ( id_post )
        REFERENCES posturi ( id_post )
    NOT DEFERRABLE;

ALTER TABLE detalii_posturi
    ADD CONSTRAINT posturi_detalii FOREIGN KEY ( id_post )
        REFERENCES posturi ( id_post )
    NOT DEFERRABLE;

CREATE OR REPLACE TRIGGER trg_pachete_BRIU 
    BEFORE INSERT OR UPDATE ON Pachete 
    FOR EACH ROW 
BEGIN
	IF( :new.data_start < TO_DATE('01.01.2000', 'DD-MM-YYYY'))
	THEN
		RAISE_APPLICATION_ERROR( -20001,
		'Data invalida: ' || TO_CHAR( :new.data_start, 'DD.MM.YYYY HH24:MI:SS' ) || ' trebuie sa fie o data mai recenta.' );
	END IF;
END; 
/

CREATE OR REPLACE TRIGGER trg_pachete_BRIU2 
    BEFORE INSERT OR UPDATE ON Pachete 
    FOR EACH ROW 
BEGIN
	IF( :new.data_end < TO_DATE('01.01.2000', 'DD-MM-YYYY'))
	THEN
		RAISE_APPLICATION_ERROR( -20001,
		'Data invalida: ' || TO_CHAR( :new.data_end, 'DD.MM.YYYY HH24:MI:SS' ) || ' trebuie sa fie o data mai recenta.' );
	END IF;
END; 
/

CREATE OR REPLACE TRIGGER trg_posturi_BRIU 
    BEFORE INSERT OR UPDATE ON Posturi 
    FOR EACH ROW 
BEGIN
	IF( :new.data_start < TO_DATE('01.01.2000', 'DD-MM-YYYY'))
	THEN
		RAISE_APPLICATION_ERROR( -20001,
		'Data invalida: ' || TO_CHAR( :new.data_start, 'DD.MM.YYYY HH24:MI:SS' ) || ' trebuie sa fie o data mai recenta.' );
	END IF;
END; 
/

CREATE OR REPLACE TRIGGER trg_posturi_BRIU2 
    BEFORE INSERT OR UPDATE ON Posturi 
    FOR EACH ROW 
BEGIN
	IF( :new.data_end < TO_DATE('01.01.2000', 'DD-MM-YYYY'))
	THEN
		RAISE_APPLICATION_ERROR( -20001,
		'Data invalida: ' || TO_CHAR( :new.data_end, 'DD.MM.YYYY HH24:MI:SS' ) || ' trebuie sa fie o data mai recenta.' );
	END IF;
END; 
/

CREATE SEQUENCE clienti_id_client_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER clienti_id_client_trg BEFORE
    INSERT ON clienti
    FOR EACH ROW
    WHEN ( new.id_client IS NULL )
BEGIN
    :new.id_client := clienti_id_client_seq.nextval;
END;
/

CREATE SEQUENCE pachete_id_pachet_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER pachete_id_pachet_trg BEFORE
    INSERT ON pachete
    FOR EACH ROW
    WHEN ( new.id_pachet IS NULL )
BEGIN
    :new.id_pachet := pachete_id_pachet_seq.nextval;
END;
/

CREATE SEQUENCE posturi_id_post_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER posturi_id_post_trg BEFORE
    INSERT ON posturi
    FOR EACH ROW
    WHEN ( new.id_post IS NULL )
BEGIN
    :new.id_post := posturi_id_post_seq.nextval;
END;
/