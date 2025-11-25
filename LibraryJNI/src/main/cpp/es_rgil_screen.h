
#ifndef ES_RGIL_SCREEN_H
#define ES_RGIL_SCREEN_H

/////////////// Types of available sounds ////////////
typedef enum
{
    ES_RGIL_SOUND_CARD_OFF = 0,/*<< No sound. */
    ES_RGIL_SOUND_CARD_OK = 1,/*<< Valid card sound. Karta prawidłowa pojedynczy sygnał wibratora oraz krótki dźwięk 3300kHz, 75ms. */
    ES_RGIL_SOUND_CARD_NG = 2,/*<< Invalid card sound. Karta nieprawidłowa, brak danych potrzebnych do sprawdzenia biletu: podwójny sygnał wibratora oraz dźwięk 300Hz przez 300ms */
    ES_RGIL_SOUND_CARD_REDUCTION = 3,/*<< Card with reduction sound. Karta prawidłowa z ważną ulgą pojedynczy sygnał wibratora oraz krótkidźwięk 3200kHz, 200ms. */
    ES_RGIL_SOUND_CARD_BLACK = 4/*<< Valid card sound. Karta na czarnej liście, zablokowana, nieaktualna: dźwięk 300Hz przez 300ms, wibrator poczwórny sygnał */
}es_rgil_sound_type;

/////////////// Types of available screens ////////////
typedef enum
{
    ES_RGIL_SCREEN_TYPE_MESSAGE,
    ES_RGIL_SCREEN_TYPE_INSPECTOR,
    ES_RGIL_SCREEN_TYPE_STATISTIC,
    ES_RGIL_SCREEN_TYPE_PERSONAL,
    ES_RGIL_SCREEN_TYPE_TICKET_STATE,
    ES_RGIL_SCREEN_TYPE_EP,
    ES_RGIL_SCREEN_TYPE_PERIODIC,
    ES_RGIL_SCREEN_TYPE_TICKET_SET,
} es_rgil_screen_type;


////// control screen definition ///////////
enum
{
    ES_RGIL_SCREEN_STATISTIC_VALUES_LEN = 3,

    ES_RGIL_SCREEN_CONTROL_NUMBER_OF_TICKETS_LEN = 17,
    ES_RGIL_SCREEN_CONTROL_CARD_NUMBER_LEN = 24,
    ES_RGIL_SCREEN_CONTROL_DATE_TIME_LEN = 19,
    ES_RGIL_SCREEN_CONTROL_VEHICLE_NO_LEN = 5,
    ES_RGIL_SCREEN_CONTROL_COURSE_LEN = 5,
    ES_RGIL_SCREEN_CONTROL_STOP_LEN = 2,
    ES_RGIL_SCREEN_CONTROL_ZONE_LEN = 1,
    ES_RGIL_SCREEN_CONTROL_LINE_LEN = 7,
    ES_RGIL_SCREEN_CONTROL_RESULT_LEN = 14,
    ES_RGIL_SCREEN_CONTROL_ALL_LINES_LEN = 32,
    ES_RGIL_SCREEN_CONTROL_FULL_CONTRACT_LEN = 60,
    ES_RGIL_SCREEN_CONTROL_SYMBOL_CONTRACT_LEN = 19,
    ES_RGIL_SCREEN_CONTROL_NAME_LEN = 100,
    ES_RGIL_SCREEN_CONTROL_PASS_TYPE_LEN = 9,
    ES_RGIL_SCREEN_CONTROL_AMOUNT_LEN = 6,
    ES_RGIL_SCREEN_CONTROL_RANGE_VALIDITY_LEN = 23,
    ES_RGIL_SCREEN_CONTROL_DAY_OF_WEEK_LEN = 22,
    ES_RGIL_SCREEN_CONTROL_ALL_ZONES_LEN = 8,
    ES_RGIL_SCREEN_CONTROL_DELETION_TYPE_LEN = 8,
    ES_RGIL_SCREEN_CONTROL_VALIDITY_DATE_LEN = 10,
    ES_RGIL_SCREEN_CONTROL_TARRIF_LEN = 1,
    ES_RGIL_SCREEN_CONTROL_REDUCT_TYPE_LEN = 9,
    ES_RGIL_SCREEN_CONTROL_REDUCT_DOC_TYPE_LEN = 50,
    ES_RGIL_SCREEN_CONTROL_REDUCT_DOC_NUMBER_LEN = 20,
    ES_RGIL_SCREEN_CONTROL_STOP_NAME_LEN = 16,

    ES_RGIL_SCREEN_MESSAGE_MSG_LEN = 50,
    /* ... */
};

//----------------------------------------ticket---------------------------------------------

typedef struct
{
    char result[ES_RGIL_SCREEN_CONTROL_RESULT_LEN + 1];//display centered
    uint32_t result_font_color; //RGBA
    uint32_t result_background_color; //RGBA
}es_rgil_screen_control_result;

typedef enum//flags - show what's on the ticket
{
    ES_RGIL_SCREEN_TICKET_IS_EP = 0x01,
    ES_RGIL_SCREEN_TICKET_IS_PERIODIC1 = 0x02,
    ES_RGIL_SCREEN_TICKET_IS_PERIODIC2 = 0x04,
    ES_RGIL_SCREEN_TICKET_IS_PERSON = 0x08,//dodano
} es_rgil_screen_ticket_what_is;

typedef enum//-------ikony dodane---------------
{
    ES_RGIL_SCREEN_TICKET_ICON_OK,
    ES_RGIL_SCREEN_TICKET_ICON_NG,
    ES_RGIL_SCREEN_TICKET_ICON_ABSENCE,
    ES_RGIL_SCREEN_TICKET_ICON_UCERTAIN,
} es_rgil_screen_ticket_icon;

typedef enum//----------------------
{
    ES_RGIL_SCREEN_BOOL_TRUE = 1,
    ES_RGIL_SCREEN_BOOL_FALSE = 0,
} es_rgil_screen_bool;


typedef struct//Stan biletu
{
    uint32_t header_background_color; //RGBA
    uint32_t header_font_color; //RGBA

    char card_number[ES_RGIL_SCREEN_CONTROL_CARD_NUMBER_LEN + 1];

    char message[ES_RGIL_SCREEN_MESSAGE_MSG_LEN + 1];
    uint32_t msg_font_color; //RGBA
    es_rgil_screen_bool is_msg;//[BJ]Okre la czy wy wietla  wiadomo   czy bilet


    //----------------PORTMONETKA------------------------------------------------
    es_rgil_screen_control_result ep_result;
    es_rgil_screen_ticket_icon ep_icon;//[BJ]-------ikony dodane---------------
    char ep_number_of_tickets[ES_RGIL_SCREEN_CONTROL_NUMBER_OF_TICKETS_LEN + 1];
    uint32_t ep_number_of_tickets_font_color;//[BJ]-----dodano kolor-----

    es_rgil_screen_bool display_expiration;//----dodano gdy ES_RGIL_SCREEN_BOOL_TRUE to wy wietli  date i czas konca waznosci oraz strefe
    char expiration_date_time[ES_RGIL_SCREEN_CONTROL_DATE_TIME_LEN + 1];//data i czas waznosci biletu czasowego
    uint32_t expiration_date_time_font_color;//-----dodano kolor
    char expiration_zone[ES_RGIL_SCREEN_CONTROL_ZONE_LEN + 1];//strefa waznosci biletu czasowego

    char ep_ng_info[ES_RGIL_SCREEN_MESSAGE_MSG_LEN + 1];//wyswietlane zamiast ep_number_of_tickets w sytuacji gdy bilet niewa ny //ES_RGIL_SCREEN_TICKET_ICON_NG
    uint32_t ep_ng_info_font_color;

    //----------------OKRESOWY1--------------------------------------------------
    es_rgil_screen_control_result periodic1_result;
    es_rgil_screen_ticket_icon periodic1_icon;//[BJ]-------ikony dodane---------------
    char periodic1_stop_name_from[ES_RGIL_SCREEN_CONTROL_STOP_NAME_LEN + 1];//przystanek wsiadania
    char periodic1_contract[ES_RGIL_SCREEN_CONTROL_FULL_CONTRACT_LEN + 1];

    //----------------OKRESOWY2--------------------------------------------------
    es_rgil_screen_control_result periodic2_result;
    es_rgil_screen_ticket_icon periodic2_icon;//[BJ]-------ikony dodane---------------
    char periodic2_stop_name_from[ES_RGIL_SCREEN_CONTROL_STOP_NAME_LEN + 1];//przystanek wsiadania
    char periodic2_contract[ES_RGIL_SCREEN_CONTROL_FULL_CONTRACT_LEN + 1];
}es_rgil_screen_ticket_state;


typedef struct//Dane osobowe
{
    char name[ES_RGIL_SCREEN_CONTROL_NAME_LEN + 1];

    char passenger_type[ES_RGIL_SCREEN_CONTROL_PASS_TYPE_LEN + 1];

    char amount_of_city_purse[ES_RGIL_SCREEN_CONTROL_AMOUNT_LEN + 1];// tytu  - Portmonetka miejska

    //----------reduction--------------------------------------------
    char reduction_type[ES_RGIL_SCREEN_CONTROL_REDUCT_TYPE_LEN + 1];
    char reduction_range_validity[ES_RGIL_SCREEN_CONTROL_RANGE_VALIDITY_LEN + 1];
    uint32_t reduction_range_validity_font_color;//[BJ]---dodano kolor

    char reduction_document_number[ES_RGIL_SCREEN_CONTROL_REDUCT_DOC_NUMBER_LEN + 1]; //numer legitymacji szkolnej - wy wietlam bez opisu w nowej linii opis poni ej
    char reduction_document_type[ES_RGIL_SCREEN_CONTROL_REDUCT_DOC_TYPE_LEN + 1]; //? Legitymacja szkolna- wy wietlam bez opisu w nowej linii opis poni ej
}es_rgil_screen_personal_data;


typedef struct//Okresowy
{
    char contract_symbol[ES_RGIL_SCREEN_CONTROL_SYMBOL_CONTRACT_LEN + 1];

    char range_validity[ES_RGIL_SCREEN_CONTROL_RANGE_VALIDITY_LEN + 1];
    uint32_t range_validity_font_color;//[BJ]-----dodano kolor

    char days_of_week[ES_RGIL_SCREEN_CONTROL_DAY_OF_WEEK_LEN + 1];
    uint32_t days_of_week_font_color;//[BJ]-----dodano kolor

    //[BJ] SIECIOWY lub 12, 145, 4F
    char lines[ES_RGIL_SCREEN_CONTROL_ALL_LINES_LEN + 1];
    uint32_t lines_font_color;//[BJ]-----dodano kolor

    //[BJ] 1,2
    char zones[ES_RGIL_SCREEN_CONTROL_ALL_ZONES_LEN + 1];
    uint32_t zones_font_color;//[BJ]-----dodano kolor

    es_rgil_screen_bool display_deletion;//----dodano gdy ES_RGIL_SCREEN_BOOL_TRUE to wy wietli  tabel  skasowania

    //------------deletion only exists if needed-------------display_deletion = ES_RGIL_SCREEN_BOOL_TRUE---------
    char deletion_date_time[ES_RGIL_SCREEN_CONTROL_DATE_TIME_LEN + 1];
    uint32_t deletion_date_time_font_color;//-----dodano kolor

    char courseid[ES_RGIL_SCREEN_CONTROL_COURSE_LEN + 1];
    uint32_t courseid_font_color;//[BJ]-----dodano kolor

    char deletion_type[ES_RGIL_SCREEN_CONTROL_DELETION_TYPE_LEN + 1];
    uint32_t deletion_type_font_color;//[BJ]-----dodano kolor

    char stop[ES_RGIL_SCREEN_CONTROL_STOP_LEN + 1];
    uint32_t stop_font_color;//[BJ]-----dodano kolor

    char zone[ES_RGIL_SCREEN_CONTROL_ZONE_LEN + 1];
    uint32_t zone_font_color;//[BJ]-----dodano kolor

    char line[ES_RGIL_SCREEN_CONTROL_LINE_LEN + 1];
    uint32_t line_font_color;//[BJ]-----dodano kolor
}es_rgil_screen_periodic_data;


typedef struct//Portmonetka
{
    char validity_date[ES_RGIL_SCREEN_CONTROL_VALIDITY_DATE_LEN + 1];
    uint32_t validity_date_font_color;//[BJ]-----dodano kolor

    char tarrif[ES_RGIL_SCREEN_CONTROL_TARRIF_LEN + 1];
    char amount_of_purse[ES_RGIL_SCREEN_CONTROL_AMOUNT_LEN + 1];
    char number_of_tickets[ES_RGIL_SCREEN_CONTROL_NUMBER_OF_TICKETS_LEN + 1];

    char expiration_date_time[ES_RGIL_SCREEN_CONTROL_DATE_TIME_LEN + 1];
    uint32_t expiration_date_time_font_color;//-----dodano kolor
    char expiration_zone[ES_RGIL_SCREEN_CONTROL_ZONE_LEN + 1];

    char deletion_date_time[ES_RGIL_SCREEN_CONTROL_DATE_TIME_LEN + 1];
    uint32_t deletion_date_time_font_color;//[BJ]-----dodano kolor

    char courseid[ES_RGIL_SCREEN_CONTROL_COURSE_LEN + 1];
    uint32_t courseid_font_color;//[BJ]-----dodano kolor

    char deletion_type[ES_RGIL_SCREEN_CONTROL_DELETION_TYPE_LEN + 1];
    uint32_t deletion_type_font_color;//[BJ]-----dodano kolor

    char stop[ES_RGIL_SCREEN_CONTROL_STOP_LEN + 1];
    uint32_t stop_font_color;//[BJ]-----dodano kolor

    char zone[ES_RGIL_SCREEN_CONTROL_ZONE_LEN + 1];
    uint32_t zone_font_color;//[BJ]-----dodano kolor

    char line[ES_RGIL_SCREEN_CONTROL_LINE_LEN + 1];
    uint32_t line_font_color;//[BJ]-----dodano kolor
}es_rgil_screen_ep_data;

//------------------------------------------------------------------------------------



typedef struct
{
    char tickets[ES_RGIL_SCREEN_STATISTIC_VALUES_LEN + 1];
    char inspections[ES_RGIL_SCREEN_STATISTIC_VALUES_LEN + 1];
    char bank_cards[ES_RGIL_SCREEN_STATISTIC_VALUES_LEN + 1];
    char reports[ES_RGIL_SCREEN_STATISTIC_VALUES_LEN + 1];
}es_rgil_screen_statistic;


typedef struct
{
    uint32_t header_background_color; //RGBA
    uint32_t header_font_color; //RGBA

    char message[ES_RGIL_SCREEN_MESSAGE_MSG_LEN + 1];
    uint32_t msg_font_color; //RGBA
    es_rgil_screen_bool is_msg;//[BJ]Okre la czy wy wietla  wiadomo   czy dane

    char card_number[ES_RGIL_SCREEN_CONTROL_CARD_NUMBER_LEN + 1];

    char deletion_date_time[ES_RGIL_SCREEN_CONTROL_DATE_TIME_LEN + 1];
    char vehicle_no[ES_RGIL_SCREEN_CONTROL_VEHICLE_NO_LEN + 1];
    char line[ES_RGIL_SCREEN_CONTROL_LINE_LEN + 1];
    char courseid[ES_RGIL_SCREEN_CONTROL_COURSE_LEN + 1];
    char stop_in[ES_RGIL_SCREEN_CONTROL_STOP_LEN + 1];
    char stop_out[ES_RGIL_SCREEN_CONTROL_STOP_LEN + 1];
    char zone_in[ES_RGIL_SCREEN_CONTROL_ZONE_LEN + 1];
    char zone_out[ES_RGIL_SCREEN_CONTROL_ZONE_LEN + 1];
    char stop_in_name[ES_RGIL_SCREEN_CONTROL_STOP_NAME_LEN + 1];
}es_rgil_screen_inspector;


typedef struct
{
    es_rgil_screen_ticket_what_is what_is;
    es_rgil_screen_ticket_state ticket_state;
    es_rgil_screen_personal_data personal_data;
    es_rgil_screen_ep_data ep_data;
    es_rgil_screen_periodic_data periodic_data_1;
    es_rgil_screen_periodic_data periodic_data_2;
    es_rgil_screen_inspector inspector;
}es_rgil_screen_ticket_set;

/////// message screen definition //////////////

typedef struct
{
    char msg[ES_RGIL_SCREEN_MESSAGE_MSG_LEN + 1];
    uint32_t msg_font_color; //RGBA
    uint32_t msg_background_color; //RGBA
    /* ... */
} es_rgil_screen_message;


////////////////generic screen union /////////////

typedef union
{
    es_rgil_screen_message message_screen;
    es_rgil_screen_statistic statistic_screen;
    es_rgil_screen_inspector inspector_screen;
    es_rgil_screen_ticket_set ticket_screen;

    /* ... */
} es_rgil_screen;

////////////// library output //////////////

typedef struct
{
    es_rgil_screen_type type;
    es_rgil_screen screen;
    es_rgil_sound_type sound_type;
} es_rgil_screens;

#endif