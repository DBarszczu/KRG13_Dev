/**
 * @file  es_rgil.h
 * @brief Integration layer between eService and RG.
 */
#ifndef ES_RGIL_H
#define ES_RGIL_H

#include <stdint.h>
#include <stdlib.h>
#include <stdio.h>
#include "es_rgil_screen.h"

#define ES_RGIL_FILE_STORAGE_CNT (1) /*<< File for internal module data (like counters etc.). */
#define ES_RGIL_FILE_REPORT      (2) /*<< File with report. */
#define ES_RGIL_FILE_STORAGE     (3) /*<< File for internal module data (like static configuration, keys etc.). */
#define ES_RGIL_FILE_BLACKLIST   (4) /*<< File with blacklist. */
#define ES_RGIL_FILE_DICTIONARY  (5) /*<< File with dictionary data. */

#define ES_RGIL_RET_OK           (0)  /*<< Function finished with success. */
#define ES_RGIL_RET_ERROR        (-1) /*<< Function finished with error. */
#define ES_RGIL_RET_ERROR_LOGIN_DISPLAY (-2) /*<< Function finished with error which must be displayed at login */

#define ES_RGIL_TOKEN_FILE_STATUS_OK               (0) /*<< Properly retrieve a list of tokens*/
#define ES_RGIL_TOKEN_FILE_STATUS_NG               (1) /*<< Error downloading token list (eg communication problems, etc.)*/
#define ES_RGIL_TOKEN_FILE_STATUS_INVALID_ID       (2) /*<< Error getting list of tokens - incorrect course id*/
#define ES_RGIL_TOKEN_FILE_STATUS_PROCESSING_ERROR (3) /*<< Error processing token file*/
#define ES_RGIL_TOKEN_FILE_STATUS_WEBSERVICE_ERROR (4) /*<< Error from webservice*/

#define ES_RGIL_BANK_CARD_STATUS_OK      (0) /*<< The card is on the list*/
#define ES_RGIL_BANK_CARD_STATUS_NG      (1) /*<< No card on the list*/
#define ES_RGIL_BANK_CARD_STATUS_NO_LIST (2) /*<< No token list*/
#define ES_RGIL_BANK_CARD_STATUS_INVALID_ZONE (3) /*<< Invalid zone*/
#define ES_RGIL_BANK_CARD_STATUS_INVALID_TICKETS     (4) /*<< Invalid tickets*/
#define ES_RGIL_BANK_CARD_STATUS_COMMUNICATION_ERROR (5) /*<< No transaction list - communication error*/
#define ES_RGIL_BANK_CARD_STATUS_WEBSERVICE_ERROR    (6) /*<< No transaction list - error from webservice*/


#define ES_RGIL_CMD_VALIDATE  (1) /*<< Validate mifare plus card command. */
#define ES_RGIL_CMD_RELOAD    (2) /*<< Called when configuration was reloaded. */
#define ES_RGIL_CMD_IDLE      (3) /*<< Called when idle static screen is needed. */
#define ES_RGIL_CMD_LOGIN     (4) /*<< Called when login is needed. */
//#define ES_RGIL_CMD_LOCK      (5) /*<< Lock card command. */
//#define ES_RGIL_CMD_UNLOCK    (6) /*<< Unlock card command. */
#define ES_RGIL_CMD_CHECK     (7) /*<< Check card command. */

#define ES_RGIL_MEDIUM_WIFI (0)
#define ES_RGIL_MEDIUM_GPRS (1)

#define ES_RGIL_MEDIUM_DISCONNECTED (0)
#define ES_RGIL_MEDIUM_CONNECTED    (1)

#define ES_RGIL_DEVICE_CHARGING (255)

typedef struct es_rgil_token_file_data
{
    int32_t connection_error_code;
    uint8_t connection_medium;
    uint8_t is_medium_connected;
    uint8_t medium_signal_percentage; /* valid only if connected */
    uint32_t gprs_plmn;
    uint8_t gprs_technology;
    uint16_t wifi_vehicle_number;
    uint8_t battery_charge_percentage;//when is charging value 255

    uint8_t  status;/*<< ES_RGIL_TOKEN_FILE_* values*/
    uint16_t vehicle_number;/*<< JSON currentcoursedata: vehiclenumber*/
    uint16_t course_id;/*<< JSON currentcoursedata: courseid*/
    uint8_t  zone_number;/*<< JSON currentcoursedata: zone*/
    uint8_t  stop_number;/*<< JSON currentcoursedata:stop:number*/
}es_rgil_token_file_data; /*<< token data structure. */

typedef struct es_rgil_bank_card_data
{
    uint8_t  status;/*<< ES_RGIL_BANK_CARD_STATUS_* values*/
    uint8_t  token[4];/*<< last four token bytes*/

    uint16_t transaction_date;/*<< JSON transactions:date - rgil_to_rg_date*/
    uint16_t transaction_time;/*<< JSON transactions:time - rgil_to_rg_time*/
    uint16_t transaction_course_id;/*<< JSON transactions:courseid*/
    uint8_t  transaction_stop_number;/*<< JSON transactions:stop:number*/
    uint8_t  transaction_zone_number;/*<< JSON transactions:zone*/

    uint8_t  transaction_all_ticket_quantities;/*<< JSON transactions:tickets:quantity sum all for transaction, valid and invalid*/
    uint8_t  transaction_ticket_quantity;/*<< JSON transactions:tickets:quantity for token and typeid*/
    uint16_t transaction_ticket_typeid;/*<< JSON transactions:tickets:typeid*/

    uint16_t validation_date;/*<< validation ticket date - rgil_to_rg_date*/
    uint16_t validation_time;/*<< validation ticket time - rgil_to_rg_time*/

    uint16_t inspection_course_id;/*<< JSON currentcoursedata: courseid*/
    uint8_t  inspection_stop_number;/*<< JSON currentcoursedata:stop:number*/
    uint16_t inspection_vehicle_number;/*<< JSON currentcoursedata: vehiclenumber*/
    uint8_t  inspection_zone_number;/*<< JSON currentcoursedata:zone:number*/

    uint8_t transaction_number_of_valid_tickets;
    uint8_t transaction_number_of_invalid_tickets;

    uint16_t json_gen_date;/*<< JSON fileinfo: gendate*/
    uint16_t json_gen_time;/*<< JSON fileinfo: gentime*/
}es_rgil_bank_card_data; /*<< card bank data structure. */

typedef struct es_rgil_course_data
{
    uint8_t uid[8];
    uint16_t vehicle_number;
    uint16_t course_id;
    uint16_t course_id_prev;
    uint8_t stop_lp_in;
    uint8_t stop_lp_out;
    uint8_t zone_in;
    uint8_t zone_out;
    uint16_t line_id;
    char line[6];
}es_rgil_course_data;

typedef struct es_rgil_negate_validation
{
    uint8_t uid[8];
    uint8_t uuid[16];
}es_rgil_negate_validation;

#ifdef __cplusplus
extern "C" {
#endif


/**
 * Read data from internal storage.
 *
 * @param fid       File id (ES_RGIL_FILE_*).
 * @param data      Buffer for data.
 * @param data_size Size of data buffer (in), number of bytes readed (out).
 *
 * @note If buffer for data is too small ES_RGIL_RET_ERROR is returned and data_size is set to required minimal value.
 *
 * @return Status of operation (ES_RGIL_RET_*).
 */
int32_t es_rgil_storage_read(uint8_t fid, uint8_t* data, size_t* data_size);

/**
 * Write data into internal storage. File is created/cleared before operation.
 *
 * @param fid       File id (ES_RGIL_FILE_*).
 * @param data      Buffer with data.
 * @param data_size Size of data buffer.
 *
 * @return Status of operation (ES_RGIL_RET_*).
 */
int32_t es_rgil_storage_write(uint8_t fid, uint8_t* data, size_t data_size);

/**
 * Append data at the end of internal storaged file.
 *
 * @param fid       File id (ES_RGIL_FILE_*).
 * @param data      Buffer with data.
 * @param data_size Buffer data size.
 *
 * @return Status of operation (ES_RGIL_RET_*).
 */
int32_t es_rgil_storage_append(uint8_t fid, uint8_t* data, size_t data_size);

/**
 * Read current time from terminal (UNIX style).
 *
 * @return Time value.
 */
time_t es_rgil_time(void);

/**
 * Read terminal identification numer.
 *
 * @param id      Buffer for data (ascii values'0001' - '0999')
 * @param id_size Buffer data size.
 *
 * @note If buffer for id is too small or ID doesn't exist ES_RGIL_RET_ERROR is returned and id_size is set to required
 *       minimal value.
 *
 * @return Status of operation (ES_RGIL_RET_*).
 */
int32_t es_rgil_id(uint8_t* id, size_t* id_size);


/**
 * Append to log file.
 * @param lines     data appended to the file.
 */
void es_rgil_log(const char* lines);


/**
 * Register get token list status
 *
 * @param es_rgil_token_file_data
 */
void rgil_register_get_token_file_status(const es_rgil_token_file_data* token_file_data);


/**
 * Register bank card validate
 *
 * @param es_rgil_bank_card_data
 */
void rgil_register_validate_bank_card(const es_rgil_bank_card_data* bank_card_data);


/**
 * Create RG date.
 *
 * @param year      year >= 2000
 * @param month     <1,12>
 * @param day       <1,31>
 *
 * @note If value out of range returns 0.kl[
 *
 * @return rg packed date.
 */
uint16_t rgil_to_rg_date(int32_t year, int32_t month, int32_t day);

/**
 * Create RG time.
 *
 * @param hour      <0,23>
 * @param minute    <0,59>
 * @param second    <0,59>
 *
 * @note If value out of range returns 0.
 *
 * @return rg packed time.
 */
uint16_t rgil_to_rg_time(int32_t hour, int32_t minute, int32_t second);

/**
 * iso14443_4 data transceiver function.
 *
 * @param command      Command data.
 * @param command_length      Command data size.
 * @param response  Response buffer.
 * @param response_length  Response buffer size.
 *
 * @return Status of operation (ES_RGIL_RET_*).
 */
int32_t es_rgil_iso14443_4_transceive(const uint8_t* command, size_t command_length, uint8_t* response, size_t* response_length);

/**
 * Module entry function.
 *
 * @param cmd      Command to execute (ES_RGIL_CMD_*).
 * @param uid      Card UID.
 * @param uid_size Size of uid buffer.
 *
 * @note  Error is returned when module cannot set message status (logical error like card is not valid should return
 * ES_RGIL_RET_OK).
 *
 * @return Status of operation (ES_RGIL_RET_*). Error is returned when module cannot set message status.
 */
int32_t rgil_entry(uint8_t cmd, const uint8_t* uid, size_t uid_size, es_rgil_screens* screens);

/**
 * Function called when received inspector card data
 * @param side number      actual bus side number value.
 */
void es_rgil_inspection_started(uint16_t side_number);

/**
 * Read terminal soft version.
 *
 * @param version      Buffer for data (ascii values 4 chars)
 * @param version_size Buffer data size.
 *
 */
void es_rgil_soft_version(uint8_t* version, size_t* version_size);

/**
 * Get course data.
 *
 * @param es_rgil_course_data filled out in the RG library.
 *
 * @return Status of operation (ES_RGIL_RET_*).
 */
int32_t rgil_get_course_data(es_rgil_course_data* course_data);

/**
 * Function called when validation passenger card completed
 * @param es_rgil_negate_validation  validation uuid and uid.
 */
void es_rgil_validation_passenger_card_completed(const es_rgil_negate_validation* data);

/**
 * Register passenger card validation negation (funkcja służy do zanegowania wysłanego raportu w Sanoku, jeśli kontroler nie zgadza się z sprawdzeniem sprawdzaki
 * dotyczącym ulgi (ktoś oszukał, że nie posiada danej ulgi na bilecie) to kontroler klika jakiś przycisk i generuje raport z negacji tego raportu.
 *
 * @param es_rgil_negate_validation
 */
void rgil_register_passenger_card_validation_negation(const es_rgil_negate_validation* data);

#ifdef __cplusplus
}
#endif

#endif