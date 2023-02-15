CREATE TABLE PLAYER(
  PLAYER_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
  BALANCE BIGINT NOT NULL,
  USERNAME VARCHAR(64)
);
CREATE TABLE TRANSACTION(
  TRANSACTION_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
  TYPE_NAME VARCHAR(32) NOT NULL,
  AMOUNT BIGINT NOT NULL,
  PLAYER_ID BIGINT NOT NULL,
  DATE_TIME DATETIME NOT NULL
);
ALTER TABLE TRANSACTION
ADD FOREIGN KEY (PLAYER_ID) REFERENCES PLAYER(PLAYER_ID);
CREATE INDEX IDX_TRANS_DATE ON TRANSACTION(DATE_TIME);