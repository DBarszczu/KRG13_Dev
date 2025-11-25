#include <jni.h>
#include <string>
#include "es_rgil.h"
#include "es_rgil_screen.h"
#include "iostream"
#include <stdio.h>
#include <stdlib.h>
#include <fstream>
#include <vector>

uint32_t GLOBAL_ES_RGIL_SCREEN_TICKET_IS;
uint32_t GLOBAL_ES_RGIL_SCREEN_TICKET_IS_EP;
uint32_t GLOBAL_ES_RGIL_SCREEN_TICKET_IS_PERIODIC1;
uint32_t GLOBAL_ES_RGIL_SCREEN_TICKET_IS_PERIODIC2;
uint32_t GLOBAL_ES_RGIL_SCREEN_TICKET_IS_PERSON;

uint32_t ticket_state_ep_icon;
uint32_t GLOBAL_ES_RGIL_SOUND_CARD;

uint32_t GLOBAL_ES_RGIL_SCREEN_TYPE_SCREEN;


std::string header_msg;
std::string message_screen_msg;

///------------------INSPEKTOR----------------------------------------------
bool screen_inspector_screen_is_msg;
std::string message;
std::string card_number;
std::string deletion_date_time;
std::string vehicle_no;
std::string line;
std::string courseid;
std::string courseid_prev;
std::string stop_in;
std::string stop_in_prev;
std::string stop_out;
std::string stop_out_prev;
std::string zone_in;
std::string zone_in_prev;
std::string zone_out;
std::string zone_out_prev;
std::string stop_in_name;
///----------------Stan Biletu------------------------------------------------
bool ticket_screen_ticket_state_is_msg;
bool ticket_state_ep_display_expiration;
uint32_t ticket_state_header_background_color;
uint32_t ticket_state_header_font_color;
std::string ticket_state_card_number;
std::string ticket_state_message;
uint32_t ticket_state_msg_font_color;
///Portmonetka
std::string ticket_state_ep_control_result;
std::uint32_t ticket_state_ep_control_result_background_color;
std::string ticket_state_ep_number_of_tickets;
uint32_t ticket_state_ep_number_of_tickets_font_color;
std::string ticket_state_ep_expiration_date_time;
uint32_t ticket_state_ep_expiration_date_time_font_color;
std::string ticket_state_ep_expiration_zone;
std::string ticket_state_ep_ng_info;
uint32_t ticket_state_ep_ng_info_font_color;
/////Okresowy 1
std::string ticket_state_periodic1_control_result;
uint32_t ticket_state_periodic1_control_result_background_color;
std::string ticket_state_periodic1_stop_name_from;
std::string ticket_state_periodic1_contract;
/////Okresowy 2
std::string ticket_state_periodic2_control_result;
uint32_t ticket_state_periodic2_control_result_background_color;
std::string ticket_state_periodic2_stop_name_from;
std::string ticket_state_periodic2_contract;
///----------------DANE osobowe------------------------------------------
std::string personal_data_name;
std::string personal_data_passenger_type;
std::string personal_data_amount_of_city_purse;
std::string personal_data_reduction_type;
std::string personal_data_reduction_range_validity;
std::string personal_data_reduction_document_number;
std::string personal_data_reduction_document_type;
///---------------------------Statystyki------------------------------------
std::string tickets;
std::string inspections;
std::string bank_cards;
std::string reports;
///----------------Bilety------------------------------------------------
///Portmonetka
std::string ticket_screen_ep_data_validity_date;
uint32_t ticket_screen_ep_data_validity_date_font_color;
std::string ticket_screen_ep_data_tarrif;
std::string ticket_screen_ep_data_amount_of_purse;
std::string ticket_screen_ep_data_number_of_tickets;
std::string ticket_screen_ep_data_expiration_date_time;
uint32_t ticket_screen_ep_data_expiration_date_time_font_color;
std::string ticket_screen_ep_data_expiration_zone;
std::string ticket_screen_ep_data_deletion_date_time;
uint32_t ticket_screen_ep_data_deletion_date_time_font_color;
std::string ticket_screen_ep_data_courseid;
uint32_t ticket_screen_ep_data_courseid_font_color;
std::string ticket_screen_ep_data_deletion_type;
uint32_t ticket_screen_ep_data_deletion_type_font_color;
std::string ticket_screen_ep_data_stop;
uint32_t ticket_screen_ep_data_stop_font_color;
std::string ticket_screen_ep_data_line;
uint32_t ticket_screen_ep_data_line_font_color;
///Okresowy 1
bool ticket_screen_periodic_data_1_display_deletion;
std::string ticket_screen_periodic_data_1_contract_symbol;
std::string ticket_screen_periodic_data_1_range_validity;
uint32_t ticket_screen_periodic_data_1_range_validity_font_color;
std::string ticket_screen_periodic_data_1_days_of_week;
uint32_t ticket_screen_periodic_data_1_days_of_week_font_color;
std::string ticket_screen_periodic_data_1_lines;
uint32_t ticket_screen_periodic_data_1_lines_font_color;
std::string ticket_screen_periodic_data_1_zones;
uint32_t ticket_screen_periodic_data_1_zones_font_color;
std::string ticket_screen_periodic_data_1_deletion_date_time;
uint32_t ticket_screen_periodic_data_1_deletion_date_time_font_color;
std::string ticket_screen_periodic_data_1_courseid;
uint32_t ticket_screen_periodic_data_1_courseid_font_color;
std::string ticket_screen_periodic_data_1_deletion_type;
uint32_t ticket_screen_periodic_data_1_deletion_type_font_color;
std::string ticket_screen_periodic_data_1_stop;
uint32_t ticket_screen_periodic_data_1_stop_font_color;
std::string ticket_screen_periodic_data_1_zone;
uint32_t ticket_screen_periodic_data_1_zone_font_color;
std::string ticket_screen_periodic_data_1_line;
uint32_t ticket_screen_periodic_data_1_line_font_color;
///Okresowy 2
bool ticket_screen_periodic_data_2_display_deletion;
std::string ticket_screen_periodic_data_2_contract_symbol;
std::string ticket_screen_periodic_data_2_range_validity;
uint32_t ticket_screen_periodic_data_2_range_validity_font_color;
std::string ticket_screen_periodic_data_2_days_of_week;
uint32_t ticket_screen_periodic_data_2_days_of_week_font_color;
std::string ticket_screen_periodic_data_2_lines;
uint32_t ticket_screen_periodic_data_2_lines_font_color;
std::string ticket_screen_periodic_data_2_zones;
uint32_t ticket_screen_periodic_data_2_zones_font_color;
std::string ticket_screen_periodic_data_2_deletion_date_time;
uint32_t ticket_screen_periodic_data_2_deletion_date_time_font_color;
std::string ticket_screen_periodic_data_2_courseid;
uint32_t ticket_screen_periodic_data_2_courseid_font_color;
std::string ticket_screen_periodic_data_2_deletion_type;
uint32_t ticket_screen_periodic_data_2_deletion_type_font_color;
std::string ticket_screen_periodic_data_2_stop;
uint32_t ticket_screen_periodic_data_2_stop_font_color;
std::string ticket_screen_periodic_data_2_zone;
uint32_t ticket_screen_periodic_data_2_zone_font_color;
std::string ticket_screen_periodic_data_2_line;
uint32_t ticket_screen_periodic_data_2_line_font_color;




jobject isoDepObject = nullptr;
jbyteArray UID = nullptr;

// Declare the JavaVM instance
JavaVM *jvm = nullptr;
// Declare the JNIEnv instance
JNIEnv *envObject = nullptr;

// JNI_OnLoad function to initialize the JNI environment
extern "C" JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved) {
    // Assign the JavaVM instance to the global variable
    jvm = vm;

    // Return the JNI version
    return JNI_VERSION_1_6;
}

// Function to retrieve the JNIEnv instance
JNIEnv *getJNIEnv() {
    JNIEnv *env;

    // Attempt to attach the current thread to the JVM
    if (jvm->AttachCurrentThread(&env, nullptr) != JNI_OK) {
        // Handle attachment failure
        return nullptr;
    }

    return env;
}


int32_t es_rgil_iso14443_4_transceive(const uint8_t *command, size_t command_length, uint8_t *response, size_t *response_length) {

    // Retrieve the JNIEnv instance.
    JNIEnv *env = getJNIEnv();
    if (env == nullptr) {
        return ES_RGIL_RET_ERROR;
    }

    // Load the Android NfcA class.
    jclass IsoDepClass = env->FindClass("com/rg/sprawdzarka/libraryjni/INfcTransceive");
    if (IsoDepClass == nullptr) {
        return ES_RGIL_RET_ERROR;
    }

//    // Create an instance of the NfcA class.
//    jobject IsoDep = env->NewObject(IsoDepClass, env->GetMethodID(IsoDepClass, "<init>", "()V"));
//    if (IsoDep == nullptr) {
//        return ES_RGIL_RET_ERROR;
//    }

// Prepare the arguments for the call
    jbyteArray commandBytesArray = nullptr;

    if (command != nullptr) {
        commandBytesArray = env->NewByteArray(command_length);

        // Check if commandBytesArray creation was successful
        if (commandBytesArray != nullptr) {
            // Copy the data from const uint8_t *command to the commandBytesArray
            env->SetByteArrayRegion(commandBytesArray, 0, command_length,
                                    reinterpret_cast<jbyte *>(const_cast<uint8_t *>(command)));
        } else {
            // Handle the case where memory allocation for commandBytesArray fails
            return ES_RGIL_RET_ERROR;
        }
    } else {
        // Handle the case where *command is nullptr
        return ES_RGIL_RET_ERROR;
    }



    // Call the `transceive()` method to send the command and receive the response.
    jmethodID transceiveMethod = env->GetMethodID(IsoDepClass, "iso14443Part4Transceive", "([B)[B");
    if (transceiveMethod == nullptr) {
        return ES_RGIL_RET_ERROR;
    }

    // Call the `transceive()` method to send the command and receive the response.
    jbyteArray responseBytesArray = static_cast<jbyteArray>(env->CallObjectMethod(isoDepObject,transceiveMethod,commandBytesArray));

    // Check for exceptions, particularly for a lost tag
    if (env->ExceptionCheck()) {
        jthrowable exception = env->ExceptionOccurred();
        env->ExceptionClear();
        jclass tagLostExceptionCls = env->FindClass("android/nfc/TagLostException");
        if (env->IsInstanceOf(exception, tagLostExceptionCls)) {
            env->DeleteLocalRef(tagLostExceptionCls);
            env->DeleteLocalRef(exception);
            env->DeleteLocalRef(responseBytesArray); // Added line to clean up
            return ES_RGIL_RET_ERROR; // Tag was lost during communication
        }
        env->DeleteLocalRef(tagLostExceptionCls);
        env->DeleteLocalRef(exception);
    }

    if (responseBytesArray == nullptr) {
        return ES_RGIL_RET_ERROR;
    }

    //   uint8_t *response,size_t *response_length

    // Get the length of the returned data
    jsize dataSize = env->GetArrayLength(responseBytesArray);

    // Check if the provided buffer is large enough to hold the data
    if (*response_length < static_cast<size_t>(dataSize)) {
        env->DeleteLocalRef(responseBytesArray);
        return ES_RGIL_RET_ERROR; // Bufor danych jest za mały
    }

    // Copy the data from the Java array to the C++ buffer
    env->GetByteArrayRegion(responseBytesArray, 0, dataSize, reinterpret_cast<jbyte *>(response));
    env->DeleteLocalRef(responseBytesArray);

    // Update the data_size parameter with the actual data size
    *response_length = static_cast<size_t>(dataSize);

    return ES_RGIL_RET_OK; // Sukces
}

const char *base_path;  // Zmienna globalna
extern "C"{
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_pathToFile(JNIEnv *env, jobject thiz, jstring path) {
    const char *tempPath = env->GetStringUTFChars(path, nullptr);

    // Przypisz ścieżkę do globalnej zmiennej
    base_path = tempPath;

//    // Po zakończeniu pracy, zwolnij zasoby
//    env->ReleaseStringUTFChars(path, tempPath);

    // Zwróć oryginalną ścieżkę lub zaktualizowaną ścieżkę
    return env->NewStringUTF(base_path);
}

static uint8_t *storage_data = nullptr;
static size_t storage_size = 0;

static uint8_t *storageCNT_data = nullptr;
static size_t storageCNT_size = 0;

static uint8_t *report_data = nullptr;
static size_t report_size = 0;

static uint8_t *blacklist_data = nullptr;
static size_t blacklist_size = 0;

static uint8_t *dictionary_data = nullptr;
static size_t dictionary_size = 0;

JNIEXPORT jbyteArray JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_dataStorage_1IN(JNIEnv *env, jobject thiz,
                                                           jbyteArray data_to_lib) {
    // Pobranie rozmiaru wejściowej tablicy bajtów
    jsize data_length = env->GetArrayLength(data_to_lib);
    if (data_length <= 0) {
        return nullptr;
    }

    // Zwolnienie poprzednich danych (jeśli istnieją)
    if (storage_data) {
        free(storage_data);
        storage_data = nullptr;
        storage_size = 0;
    }

    // Alokacja pamięci dla nowych danych
    storage_data = (uint8_t *) malloc(data_length);
    if (!storage_data) {
        return nullptr;  // Błąd alokacji
    }

    // Skopiowanie danych z wejściowej tablicy do dynamicznie alokowanej pamięci
    env->GetByteArrayRegion(data_to_lib, 0, data_length, reinterpret_cast<jbyte *>(storage_data));
    storage_size = static_cast<size_t>(data_length);

    // Zwrot nowej tablicy jako potwierdzenie (lub null w razie błędu)
    jbyteArray result = env->NewByteArray(data_length);
    if (result) {
        env->SetByteArrayRegion(result, 0, data_length, reinterpret_cast<jbyte *>(storage_data));
    }
    return result;
}

JNIEXPORT jbyteArray JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_dataStorage_1OUT(JNIEnv *env, jobject thiz) {
    if (!storage_data || storage_size == 0) {
        return nullptr;  // Brak danych do zwrócenia
    }

    // Tworzenie nowej tablicy bajtów do zwrotu danych
    jbyteArray result = env->NewByteArray(storage_size);
    if (result) {
        env->SetByteArrayRegion(result, 0, storage_size, reinterpret_cast<jbyte *>(storage_data));
    }
    return result;
}

JNIEXPORT jbyteArray JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_dataStorage_1CNT_1IN(JNIEnv *env, jobject thiz,
                                                                jbyteArray data_to_lib) {
    // Pobranie rozmiaru wejściowej tablicy bajtów
    jsize data_length = env->GetArrayLength(data_to_lib);
    if (data_length <= 0) {
        return nullptr;
    }

    // Zwolnienie poprzednich danych (jeśli istnieją)
    if (storageCNT_data) {
        free(storageCNT_data);
        storageCNT_data = nullptr;
        storageCNT_size = 0;
    }

    // Alokacja pamięci dla nowych danych
    storageCNT_data = (uint8_t *) malloc(data_length);
    if (!storageCNT_data) {
        return nullptr;  // Błąd alokacji
    }

    // Skopiowanie danych z wejściowej tablicy do dynamicznie alokowanej pamięci
    env->GetByteArrayRegion(data_to_lib, 0, data_length, reinterpret_cast<jbyte *>(storageCNT_data));
    storageCNT_size = static_cast<size_t>(data_length);

    // Zwrot nowej tablicy jako potwierdzenie (lub null w razie błędu)
    jbyteArray result = env->NewByteArray(data_length);
    if (result) {
        env->SetByteArrayRegion(result, 0, data_length, reinterpret_cast<jbyte *>(storageCNT_data));
    }
    return result;
}
JNIEXPORT jbyteArray JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_dataSTORAGE_1CNT_1OUT(JNIEnv *env, jobject thiz) {
    if (!storageCNT_data || storageCNT_size == 0) {
        return nullptr;  // Brak danych do zwrócenia
    }

    // Tworzenie nowej tablicy bajtów do zwrotu danych
    jbyteArray result = env->NewByteArray(storageCNT_size);
    if (result) {
        env->SetByteArrayRegion(result, 0, storageCNT_size, reinterpret_cast<jbyte *>(storageCNT_data));
    }
    return result;
}

JNIEXPORT jbyteArray JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_dataReport_1IN(JNIEnv *env, jobject thiz,
                                                          jbyteArray data_to_lib) {
    // Sprawdzenie, czy wejściowa tablica istnieje i ma dane
    if (data_to_lib == nullptr || env->GetArrayLength(data_to_lib) == 0) {
        // Zwolnienie wcześniejszych danych, jeśli istniały
        if (report_data) {
            free(report_data);
            report_data = nullptr;
            report_size = 0;
        }
        return nullptr; // Brak danych wejściowych
    }

    // Pobranie rozmiaru wejściowej tablicy bajtów
    jsize data_length = env->GetArrayLength(data_to_lib);

    // Zwolnienie poprzednich danych (jeśli istnieją)
    if (report_data) {
        free(report_data);
        report_data = nullptr;
        report_size = 0;
    }

    // Alokacja pamięci dla nowych danych
    report_data = (uint8_t *) malloc(data_length);
    if (!report_data) {
        report_size = 0;
        return nullptr;  // Błąd alokacji pamięci
    }

    // Skopiowanie danych z wejściowej tablicy do dynamicznie alokowanej pamięci
    env->GetByteArrayRegion(data_to_lib, 0, data_length, reinterpret_cast<jbyte *>(report_data));
    report_size = static_cast<size_t>(data_length);

    // Tworzenie nowej tablicy bajtów do zwrócenia
    jbyteArray result = env->NewByteArray(data_length);
    if (!result) {
        free(report_data);
        report_data = nullptr;
        report_size = 0;
        return nullptr; // Błąd alokacji JNI
    }

    // Skopiowanie danych do wyniku
    env->SetByteArrayRegion(result, 0, data_length, reinterpret_cast<jbyte *>(report_data));

    return result;
}

JNIEXPORT jbyteArray JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_dataReport_1OUT(JNIEnv *env, jobject thiz) {
    // Sprawdzenie, czy istnieją dane do zwrócenia
    if (!report_data || report_size == 0) {
        // Zwrócenie pustej tablicy zamiast nullptr
        return env->NewByteArray(0);
    }

    // Tworzenie nowej tablicy bajtów do zwrotu danych
    jbyteArray result = env->NewByteArray(report_size);
    if (!result) {
        return env->NewByteArray(0); // Zwrócenie pustej tablicy w przypadku błędu alokacji
    }

    // Skopiowanie danych do wyniku
    env->SetByteArrayRegion(result, 0, report_size, reinterpret_cast<jbyte *>(report_data));

    return result;
}

JNIEXPORT jbyteArray JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_dataBlacklist_1IN(JNIEnv *env, jobject thiz,
                                                             jbyteArray data_to_lib) {
    // Pobranie rozmiaru wejściowej tablicy bajtów
    jsize data_length = env->GetArrayLength(data_to_lib);
    if (data_length <= 0) {
        return nullptr;
    }

    // Zwolnienie poprzednich danych (jeśli istnieją)
    if (blacklist_data) {
        free(blacklist_data);
        blacklist_data = nullptr;
        blacklist_size = 0;
    }

    // Alokacja pamięci dla nowych danych
    blacklist_data = (uint8_t *) malloc(data_length);
    if (!blacklist_data) {
        return nullptr;  // Błąd alokacji
    }

    // Skopiowanie danych z wejściowej tablicy do dynamicznie alokowanej pamięci
    env->GetByteArrayRegion(data_to_lib, 0, data_length, reinterpret_cast<jbyte *>(blacklist_data));
    blacklist_size = static_cast<size_t>(data_length);

    // Zwrot nowej tablicy jako potwierdzenie (lub null w razie błędu)
    jbyteArray result = env->NewByteArray(data_length);
    if (result) {
        env->SetByteArrayRegion(result, 0, data_length, reinterpret_cast<jbyte *>(blacklist_data));
    }
    return result;
}
JNIEXPORT jbyteArray JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_dataBlacklist_1OUT(JNIEnv *env, jobject thiz) {
    if (!blacklist_data || blacklist_size == 0) {
        return nullptr;  // Brak danych do zwrócenia
    }

    // Tworzenie nowej tablicy bajtów do zwrotu danych
    jbyteArray result = env->NewByteArray(blacklist_size);
    if (result) {
        env->SetByteArrayRegion(result, 0, blacklist_size, reinterpret_cast<jbyte *>(blacklist_data));
    }
    return result;
}
JNIEXPORT jbyteArray JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_dataDictionary_1IN(JNIEnv *env, jobject thiz,
                                                              jbyteArray data_to_lib) {
    // Pobranie rozmiaru wejściowej tablicy bajtów
    jsize data_length = env->GetArrayLength(data_to_lib);
    if (data_length <= 0) {
        return nullptr;
    }

    // Zwolnienie poprzednich danych (jeśli istnieją)
    if (dictionary_data) {
        free(dictionary_data);
        dictionary_data = nullptr;
        dictionary_size = 0;
    }

    // Alokacja pamięci dla nowych danych
    dictionary_data = (uint8_t *) malloc(data_length + 1); // +1 dla znaku null
    if (!dictionary_data) {
        return nullptr;  // Błąd alokacji
    }

    // Skopiowanie danych z wejściowej tablicy do dynamicznie alokowanej pamięci
    env->GetByteArrayRegion(data_to_lib, 0, data_length,
                            reinterpret_cast<jbyte *>(dictionary_data));
    dictionary_size = static_cast<size_t>(data_length);

    // Zwrot nowej tablicy jako potwierdzenie (lub null w razie błędu)
    jbyteArray result = env->NewByteArray(data_length);
    if (result) {
        env->SetByteArrayRegion(result, 0, data_length, reinterpret_cast<jbyte *>(dictionary_data));
    }

    return result;
}
JNIEXPORT jbyteArray JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_dataDictionary_1OUT(JNIEnv *env, jobject thiz) {
    if (!dictionary_data || dictionary_size == 0) {
        return nullptr;  // Brak danych do zwrócenia
    }

    // Tworzenie nowej tablicy bajtów do zwrotu danych
    jbyteArray result = env->NewByteArray(dictionary_size);
    if (result) {
        env->SetByteArrayRegion(result, 0, dictionary_size,
                                reinterpret_cast<jbyte *>(dictionary_data));
    }
    return result;
}
}
//const char *base_path = "/data/data/com.rg.sprawdzarka.jastrzebie/files/";

int32_t es_rgil_storage_read(uint8_t fid, uint8_t *data, size_t *data_size) {
    const char *filename;

    switch (fid) {
        case ES_RGIL_FILE_STORAGE_CNT:
            // Sprawdzenie, czy dane są dostępne
            if (storageCNT_data == nullptr || storageCNT_size == 0) {
                return -1; // Dane niedostępne
            }
            // Skopiowanie danych do bufora użytkownika
            memcpy(data, storageCNT_data, storageCNT_size);
            *data_size = storageCNT_size;
            return ES_RGIL_RET_OK; // Sukces
            break;
        case ES_RGIL_FILE_STORAGE:
            // Sprawdzenie, czy dane są dostępne
            if (storage_data == nullptr || storage_size == 0) {
                return -1; // Dane niedostępne
            }
            // Skopiowanie danych do bufora użytkownika
            memcpy(data, storage_data, storage_size);
            *data_size = storage_size;
            return ES_RGIL_RET_OK; // Sukces

        case ES_RGIL_FILE_REPORT:
            // Sprawdzenie, czy plik istnieje i ma dane
            if (report_data == nullptr || report_size == 0) {
                // Ustawienie pustego wyniku
                if (data != nullptr) {
                    *data = 0; // Lub ustawienie całego bufora na 0 jeśli to tablica
                }
                if (data_size != nullptr) {
                    *data_size = 0;
                }
                return -1; // Brak danych
            }

            // Skopiowanie danych do bufora użytkownika
            memcpy(data, report_data, report_size);
            *data_size = report_size;
            return ES_RGIL_RET_OK; // Sukces


        case ES_RGIL_FILE_BLACKLIST:
            // Sprawdzenie, czy dane są dostępne
            if (blacklist_data == nullptr || blacklist_size == 0) {
                return -1; // Dane niedostępne
            }
            // Skopiowanie danych do bufora użytkownika
            memcpy(data, blacklist_data, blacklist_size);
            *data_size = blacklist_size;
            return ES_RGIL_RET_OK; // Sukces

        case ES_RGIL_FILE_DICTIONARY:
            // Sprawdzenie, czy dane są dostępne
            if (dictionary_data == nullptr || dictionary_size == 0) {
                return -1; // Dane niedostępne
            }
            // Skopiowanie danych do bufora użytkownika
            memcpy(data, dictionary_data, dictionary_size);
            *data_size = dictionary_size;
            return ES_RGIL_RET_OK; // Sukces
        default:
            return ES_RGIL_RET_ERROR;
    }
}

int32_t es_rgil_storage_write(uint8_t fid, uint8_t *data, size_t data_size) {
    const char *filename;

    switch (fid) {
        case ES_RGIL_FILE_STORAGE_CNT:
            // Sprawdzenie, czy dane są dostępne
            if (data == nullptr || data_size == 0) {
                return -1; // Dane niedostępne
            }

            // Alokacja pamięci dla `storage_data`, jeśli to konieczne
            if (storageCNT_data != nullptr) {
                delete[] storageCNT_data;
            }
            storageCNT_data = new uint8_t[data_size];  // Dynamiczna alokacja pamięci
            if (!storageCNT_data) {
                return ES_RGIL_RET_ERROR; // Błąd alokacji pamięci
            }

            // Kopiowanie danych z `data` do `storage_data`
            memcpy(storageCNT_data, data, data_size);
            storageCNT_size = data_size;  // Przechowywanie rozmiaru

            return ES_RGIL_RET_OK; // Sukces

            break;
        case ES_RGIL_FILE_STORAGE:
            // Sprawdzenie, czy dane są dostępne
            if (data == nullptr || data_size == 0) {
                return -1; // Dane niedostępne
            }

            // Alokacja pamięci dla `storage_data`, jeśli to konieczne
            if (storage_data != nullptr) {
                delete[] storage_data;
            }
            storage_data = new uint8_t[data_size];  // Dynamiczna alokacja pamięci
            if (!storage_data) {
                return ES_RGIL_RET_ERROR; // Błąd alokacji pamięci
            }

            // Kopiowanie danych z `data` do `storage_data`
            memcpy(storage_data, data, data_size);
            storage_size = data_size;  // Przechowywanie rozmiaru

            return ES_RGIL_RET_OK; // Sukces

        case ES_RGIL_FILE_REPORT:
            if (data == nullptr || data_size == 0) {
                return -1; // Brak danych do zapisania
            }

            if (report_data == nullptr) {
                // Pierwsza alokacja pamięci
                report_data = new (std::nothrow) uint8_t[data_size];
                if (!report_data) {
                    return ES_RGIL_RET_ERROR; // Błąd alokacji pamięci
                }

                // Kopiowanie nowych danych
                memcpy(report_data, data, data_size);
                report_size = data_size;
            } else {
                // Obliczenie nowego rozmiaru
                size_t new_size = report_size + data_size;

                // Alokacja nowego bloku pamięci
                uint8_t *new_storage = new (std::nothrow) uint8_t[new_size];
                if (!new_storage) {
                    return ES_RGIL_RET_ERROR; // Błąd alokacji pamięci
                }

                // Kopiowanie starej zawartości do nowego bloku pamięci
                memcpy(new_storage, report_data, report_size);

                // Dopisanie nowych danych na końcu
                memcpy(new_storage + report_size, data, data_size);

                // Zwolnienie starej pamięci
                delete[] report_data;

                // Przypisanie nowego wskaźnika
                report_data = new_storage;
                report_size = new_size;
            }

            return ES_RGIL_RET_OK; // Sukces



        case ES_RGIL_FILE_BLACKLIST:
            // Sprawdzenie, czy dane są dostępne
            if (data == nullptr || data_size == 0) {
                return -1; // Dane niedostępne
            }

            // Alokacja pamięci dla `storage_data`, jeśli to konieczne
            if (blacklist_data != nullptr) {
                delete[] blacklist_data;
            }
            blacklist_data = new uint8_t[data_size];  // Dynamiczna alokacja pamięci
            if (!blacklist_data) {
                return ES_RGIL_RET_ERROR; // Błąd alokacji pamięci
            }

            // Kopiowanie danych z `data` do `storage_data`
            memcpy(blacklist_data, data, data_size);
            blacklist_size = data_size;  // Przechowywanie rozmiaru

            return ES_RGIL_RET_OK; // Sukces
        case ES_RGIL_FILE_DICTIONARY:
            // Sprawdzenie, czy dane są dostępne
            if (data == nullptr || data_size == 0) {
                return -1; // Dane niedostępne
            }

            // Alokacja pamięci dla `storage_data`, jeśli to konieczne
            if (dictionary_data != nullptr) {
                delete[] dictionary_data;
            }
            dictionary_data = new uint8_t[data_size];  // Dynamiczna alokacja pamięci
            if (!dictionary_data) {
                return ES_RGIL_RET_ERROR; // Błąd alokacji pamięci
            }

            // Kopiowanie danych z `data` do `storage_data`
            memcpy(dictionary_data, data, data_size);
            dictionary_size = data_size;  // Przechowywanie rozmiaru
        default:
            return ES_RGIL_RET_ERROR;
    }
}


int32_t es_rgil_storage_append(uint8_t fid, uint8_t *data, size_t data_size) {
    const char *filename;

    switch (fid) {
        case ES_RGIL_FILE_STORAGE_CNT:

            if (data == nullptr || data_size == 0) {
                return -1; // Brak danych do zapisania
            }
            if (storageCNT_data == nullptr) {
                storageCNT_data = new uint8_t[data_size];
                if (!storageCNT_data) {
                    return ES_RGIL_RET_ERROR; // Błąd alokacji pamięci
                }
                memcpy(storageCNT_data, data, data_size);
                storageCNT_size = data_size;
            } else {
                size_t new_size = storageCNT_size + data_size;
                uint8_t *new_storage = new uint8_t[new_size];
                if (!new_storage) {
                    return ES_RGIL_RET_ERROR; // Błąd alokacji pamięci
                }
                memcpy(new_storage, storageCNT_data, storageCNT_size); // Kopiowanie starej zawartości
                memcpy(new_storage + storageCNT_size, data, data_size); // Dopisanie nowych danych
                delete[] storageCNT_data;  // Zwolnienie starej pamięci
                storageCNT_data = new_storage;
                storageCNT_size = new_size;
            }

            return ES_RGIL_RET_OK; // Sukces

        case ES_RGIL_FILE_STORAGE:

            if (data == nullptr || data_size == 0) {
                return -1; // Brak danych do zapisania
            }
            if (storage_data == nullptr) {
                storage_data = new uint8_t[data_size];
                if (!storage_data) {
                    return ES_RGIL_RET_ERROR; // Błąd alokacji pamięci
                }
                memcpy(storage_data, data, data_size);
                storage_size = data_size;
            } else {
                size_t new_size = storage_size + data_size;
                uint8_t *new_storage = new uint8_t[new_size];
                if (!new_storage) {
                    return ES_RGIL_RET_ERROR; // Błąd alokacji pamięci
                }
                memcpy(new_storage, storage_data, storage_size); // Kopiowanie starej zawartości
                memcpy(new_storage + storage_size, data, data_size); // Dopisanie nowych danych
                delete[] storage_data;  // Zwolnienie starej pamięci
                storage_data = new_storage;
                storage_size = new_size;
            }

            return ES_RGIL_RET_OK; // Sukces

        case ES_RGIL_FILE_REPORT:

            if (data == nullptr || data_size == 0) {
                return -1; // Brak danych do zapisania
            }
            if (report_data == nullptr) {
                report_data = new uint8_t[data_size];
                if (!report_data) {
                    return ES_RGIL_RET_ERROR; // Błąd alokacji pamięci
                }
                memcpy(report_data, data, data_size);
                report_size = data_size;
            } else {
                size_t new_size = report_size + data_size;
                uint8_t *new_storage = new uint8_t[new_size];
                if (!new_storage) {
                    return ES_RGIL_RET_ERROR; // Błąd alokacji pamięci
                }
                memcpy(new_storage, report_data, report_size); // Kopiowanie starej zawartości
                memcpy(new_storage + report_size, data, data_size); // Dopisanie nowych danych
                delete[] report_data;  // Zwolnienie starej pamięci
                report_data = new_storage;
                report_size = new_size;
            }

            return ES_RGIL_RET_OK; // Sukces

        case ES_RGIL_FILE_BLACKLIST:

            if (data == nullptr || data_size == 0) {
                return -1; // Brak danych do zapisania
            }
            if (blacklist_data == nullptr) {
                blacklist_data = new uint8_t[data_size];
                if (!blacklist_data) {
                    return ES_RGIL_RET_ERROR; // Błąd alokacji pamięci
                }
                memcpy(blacklist_data, data, data_size);
                blacklist_size = data_size;
            } else {
                size_t new_size = blacklist_size + data_size;
                uint8_t *new_storage = new uint8_t[new_size];
                if (!new_storage) {
                    return ES_RGIL_RET_ERROR; // Błąd alokacji pamięci
                }
                memcpy(new_storage, blacklist_data, blacklist_size); // Kopiowanie starej zawartości
                memcpy(new_storage + blacklist_size, data, data_size); // Dopisanie nowych danych
                delete[] blacklist_data;  // Zwolnienie starej pamięci
                blacklist_data = new_storage;
                blacklist_size = new_size;
            }

            return ES_RGIL_RET_OK; // Sukces
        case ES_RGIL_FILE_DICTIONARY:
            if (data == nullptr || data_size == 0) {
                return -1; // Brak danych do zapisania
            }
            if (dictionary_data == nullptr) {
                dictionary_data = new uint8_t[data_size];
                if (!dictionary_data) {
                    return ES_RGIL_RET_ERROR; // Błąd alokacji pamięci
                }
                memcpy(dictionary_data, data, data_size);
                dictionary_size = data_size;
            } else {
                size_t new_size = dictionary_size + data_size;
                uint8_t *new_storage = new uint8_t[new_size];
                if (!new_storage) {
                    return ES_RGIL_RET_ERROR; // Błąd alokacji pamięci
                }
                memcpy(new_storage, dictionary_data, dictionary_size); // Kopiowanie starej zawartości
                memcpy(new_storage + dictionary_size, data, data_size); // Dopisanie nowych danych
                delete[] dictionary_data;  // Zwolnienie starej pamięci
                dictionary_data = new_storage;
                dictionary_size = new_size;
            }

            return ES_RGIL_RET_OK; // Sukces
        default:
            return ES_RGIL_RET_ERROR;
    }
}

time_t es_rgil_time(void) {
    time_t current_time;
    struct tm *time_info;

    time(&current_time);
    time_info = localtime(&current_time);

    printf("Current time: %s",
           asctime(time_info)); // Opcjonalne - wyświetla bieżący czas na ekranie.

    return current_time;
}

uint32_t checkerID;

extern "C"
// JNI function to set the checkerID
JNIEXPORT jint JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_checker_1ID(JNIEnv *env, jobject thiz, jint id) {
    checkerID = id;
    return checkerID;  // Return the set ID for confirmation.
}


int32_t es_rgil_id(uint8_t * id, size_t * id_size)
{
    if (id == NULL) {
        return ES_RGIL_RET_ERROR;  // Zakończenie funkcji, jeśli bufor jest NULL
    }

    // Obliczamy, ile miejsca potrzebujemy na ciąg znaków (bez null terminatora)
    int needed_size = snprintf(NULL, 0, "%u", checkerID);

    if (*id_size < (size_t)needed_size) {
        *id_size = needed_size;  // Informujemy wywołującego o wymaganym rozmiarze
        return ES_RGIL_RET_ERROR;  // Zakończenie funkcji, jeśli bufor jest za mały
    }

    *id_size = needed_size;  // Ustawienie faktycznego rozmiaru danych

    // Formatujemy liczbę jako ciąg znaków
    snprintf((char *)id, *id_size + 1, "%u", checkerID);  // Uwaga: plus jeden dla null terminatora

    return ES_RGIL_RET_OK;
}

/**
 * Append to log file.
 * @param lines     data appended to the file.
 */
void es_rgil_log(const char *lines);


/**
 * Register get token list status
 *
 * @param es_rgil_token_file_data
 */
void rgil_register_get_token_file_status(const es_rgil_token_file_data *token_file_data);

extern "C"
JNIEXPORT void JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_register_1validate_1bank_1card(JNIEnv *env, jobject thiz, jobject bank_card_data) {
    jclass cls = env->GetObjectClass(bank_card_data);
    jfieldID fid;

    fid = env->GetFieldID(cls, "status", "I");
    jint status = env->GetIntField(bank_card_data, fid);

    fid = env->GetFieldID(cls, "token", "[B");
    jbyteArray tokenArray = (jbyteArray)env->GetObjectField(bank_card_data, fid);
    jbyte *token = env->GetByteArrayElements(tokenArray, NULL);

    fid = env->GetFieldID(cls, "transactionDateTime", "Ljava/time/LocalDateTime;");
    jobject transactionDateTime = env->GetObjectField(bank_card_data, fid);
    jclass ldtCls = env->FindClass("java/time/LocalDateTime");
    jmethodID getYear = env->GetMethodID(ldtCls, "getYear", "()I");
    jmethodID getMonthValue = env->GetMethodID(ldtCls, "getMonthValue", "()I");
    jmethodID getDayOfMonth = env->GetMethodID(ldtCls, "getDayOfMonth", "()I");
    jmethodID getHour = env->GetMethodID(ldtCls, "getHour", "()I");
    jmethodID getMinute = env->GetMethodID(ldtCls, "getMinute", "()I");
    jmethodID getSecond = env->GetMethodID(ldtCls, "getSecond", "()I");

    jint year = env->CallIntMethod(transactionDateTime, getYear);
    jint month = env->CallIntMethod(transactionDateTime, getMonthValue);
    jint day = env->CallIntMethod(transactionDateTime, getDayOfMonth);
    jint hour = env->CallIntMethod(transactionDateTime, getHour);
    jint minute = env->CallIntMethod(transactionDateTime, getMinute);
    jint second = env->CallIntMethod(transactionDateTime, getSecond);

    fid = env->GetFieldID(cls, "transactionCourseId", "I");
    jint transactionCourseId = env->GetIntField(bank_card_data, fid);

    fid = env->GetFieldID(cls, "transactionStopNumber", "I");
    jint transactionStopNumber = env->GetIntField(bank_card_data, fid);

    fid = env->GetFieldID(cls, "transactionZoneNumber", "I");
    jint transactionZoneNumber = env->GetIntField(bank_card_data, fid);

    fid = env->GetFieldID(cls, "transactionAllTicketQuantities", "I");
    jint transactionAllTicketQuantities = env->GetIntField(bank_card_data, fid);

    fid = env->GetFieldID(cls, "transactionTicketQuantity", "I");
    jint transactionTicketQuantity = env->GetIntField(bank_card_data, fid);

    fid = env->GetFieldID(cls, "transactionTicketTypeid", "I");
    jint transactionTicketTypeid = env->GetIntField(bank_card_data, fid);

    fid = env->GetFieldID(cls, "inspectionCourseId", "I");
    jint inspectionCourseId = env->GetIntField(bank_card_data, fid);

    fid = env->GetFieldID(cls, "inspectionStopNumber", "I");
    jint inspectionStopNumber = env->GetIntField(bank_card_data, fid);

    fid = env->GetFieldID(cls, "inspectionVehicleNumber", "I");
    jint inspectionVehicleNumber = env->GetIntField(bank_card_data, fid);

    fid = env->GetFieldID(cls, "inspectionZoneNumber", "I");
    jint inspectionZoneNumber = env->GetIntField(bank_card_data, fid);

    fid = env->GetFieldID(cls, "transactionNumberOfValidTickets", "I");
    jint transactionNumberOfValidTickets = env->GetIntField(bank_card_data, fid);

    fid = env->GetFieldID(cls, "transactionNumberOfInvalidTickets", "I");
    jint transactionNumberOfInvalidTickets = env->GetIntField(bank_card_data, fid);

    es_rgil_bank_card_data data;
    data.status = static_cast<uint8_t>(status);
    memcpy(data.token, token, sizeof(data.token)); // zakładamy, że token ma co najmniej 4 bajty.

    data.transaction_date = rgil_to_rg_date(year, month, day);
    data.transaction_time = rgil_to_rg_time(hour, minute, second);
    data.transaction_course_id = static_cast<uint16_t>(transactionCourseId);
    data.transaction_stop_number = static_cast<uint8_t>(transactionStopNumber);
    data.transaction_zone_number = static_cast<uint8_t>(transactionZoneNumber);

    data.transaction_all_ticket_quantities = static_cast<uint8_t>(transactionAllTicketQuantities);
    data.transaction_ticket_quantity = static_cast<uint8_t>(transactionTicketQuantity);
    data.transaction_ticket_typeid = static_cast<uint16_t>(transactionTicketTypeid);

    data.validation_date = rgil_to_rg_date(year, month, day);
    data.validation_time = rgil_to_rg_time(hour, minute, second);

    data.inspection_course_id = static_cast<uint8_t>(inspectionCourseId);
    data.inspection_stop_number = static_cast<uint8_t>(inspectionStopNumber);
    data.inspection_vehicle_number = static_cast<uint8_t>(inspectionVehicleNumber);
    data.inspection_zone_number =static_cast<uint8_t>(inspectionZoneNumber);

    data.transaction_number_of_valid_tickets = static_cast<uint8_t>(transactionNumberOfValidTickets);
    data.transaction_number_of_invalid_tickets = static_cast<uint8_t>(transactionNumberOfInvalidTickets);

    data.json_gen_date = rgil_to_rg_date(year, month, day);
    data.json_gen_time = rgil_to_rg_time(hour, minute, second);

    env->ReleaseByteArrayElements(tokenArray, token, 0);

    rgil_register_validate_bank_card(&data);
}

void rgil_register_validate_bank_card(const es_rgil_bank_card_data *bank_card_data);

uint16_t rgil_to_rg_date(int32_t year, int32_t month, int32_t day);

uint16_t rgil_to_rg_time(int32_t hour, int32_t minute, int32_t second);


/**
 * Function called when received inspector card data
 * @param side number      actual bus side number value.
 */
void es_rgil_inspection_started(uint16_t side_number) {
    side_number = 275;
}
uint32_t softID;

extern "C"
JNIEXPORT jint JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_soft_1ID(JNIEnv *env, jobject thiz, jint id) {
    softID = id;
    return softID;  // Return the set ID for confirmation.
}

void es_rgil_soft_version(uint8_t * version, size_t * version_size){
    if (version == NULL) {
        return;  // Zakończenie funkcji, jeśli bufor jest NULL
    }

    // Obliczamy, ile miejsca potrzebujemy na ciąg znaków (bez null terminatora)
    int needed_size = snprintf(NULL, 0, "%u", softID);

    if (*version_size < (size_t)needed_size) {
        *version_size = needed_size;  // Informujemy wywołującego o wymaganym rozmiarze
        return;  // Zakończenie funkcji, jeśli bufor jest za mały
    }

    *version_size = needed_size;  // Ustawienie faktycznego rozmiaru danych

    // Formatujemy liczbę jako ciąg znaków
    snprintf((char *)version, *version_size + 1, "%u", softID);  // Uwaga: plus jeden dla null terminatora
}


/**
 * Get course data.
 *
 * @param es_rgil_course_data filled out in the RG library.
 *
 * @return Status of operation (ES_RGIL_RET_*).
 */
int32_t rgil_get_course_data(es_rgil_course_data * course_data);

/**
 * Function called when validation passenger card completed
 * @param es_rgil_negate_validation  validation uuid and uid.
 */
void es_rgil_validation_passenger_card_completed(const es_rgil_negate_validation * data){

}

/**
 * Register passenger card validation negation
 *
 * @param es_rgil_negate_validation
 */

//void rgil_register_passenger_card_validation_negation(const es_rgil_negate_validation * data) {
//
//}
#include <string>
#include <map>

std::string replaceSpecialChars(const std::string &input) {
    std::map<std::string, std::string> replacements = {
            {"\xa5", "Ą"}, {"\xb9", "ą"}, {"\xc6", "Ć"}, {"\xe6", "ć"},
            {"\xca", "Ę"}, {"\xea", "ę"}, {"\xA3", "Ł"}, {"\xB3", "ł"},
            {"\xD1", "Ń"}, {"\xF1", "ń"}, {"\xD3", "Ó"}, {"\xF3", "ó"},
            {"\x8C", "Ś"}, {"\x9C", "ś"}, {"\x8F", "Ź"}, {"\x9F", "ź"},
            {"\xAF", "Ż"}, {"\xbf", "ż"}, {"\xff", "˙"}
    };

    std::string modifiedString = input;
    for (const auto& [key, value] : replacements) {
        size_t startPos = 0;
        while ((startPos = modifiedString.find(key, startPos)) != std::string::npos) {
            modifiedString.replace(startPos, key.length(), value);
            startPos += value.length();
        }
    }
    return modifiedString;
}
extern "C" {

jstring createJniString(JNIEnv *env, const std::string &nativeString) {
    // Najpierw zamieniamy wszystkie wystąpienia \xaf na ż
    std::string modifiedString = replaceSpecialChars(nativeString);

    // Konwertuj zmodyfikowany C++ UTF-8 string na Java String (UTF-16)
    return env->NewStringUTF(modifiedString.c_str());
}
jint createJniLong(JNIEnv *env, const std::atomic_ulong &nativeInt) {
    // Directly return the native C++ unsigned integer as jint
    return static_cast<jint>(nativeInt);
}
jint createJniInt(JNIEnv *env, const std::atomic_uint &nativeInt) {
    // Directly return the native C++ unsigned integer as jint
    return static_cast<jint>(nativeInt);
}


JNIEXPORT jint JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_LOGIN(JNIEnv *env, jobject thiz, jobject iso_dep, jbyteArray uid_num) {
    isoDepObject = iso_dep;

    jsize len;
    len = env->GetArrayLength(uid_num);
    auto *uidBytes = new uint8_t[len];
    env->GetByteArrayRegion(uid_num, 0, len, reinterpret_cast<jbyte *>(uidBytes));

    es_rgil_screens screens = {static_cast<es_rgil_screen_type>(0)};

    int32_t LOGIN = rgil_entry(ES_RGIL_CMD_LOGIN, uidBytes, len, &screens);

    // Usuń zaalokowany bufor uidBytes
    delete[] uidBytes;

    message_screen_msg = screens.screen.message_screen.msg;

    ///----------------Typ Ekranu------------------------------------------------
    if (screens.type == ES_RGIL_SCREEN_TYPE_MESSAGE) {
        GLOBAL_ES_RGIL_SCREEN_TYPE_SCREEN = 1;
    } else if (screens.type == ES_RGIL_SCREEN_TYPE_INSPECTOR) {
        GLOBAL_ES_RGIL_SCREEN_TYPE_SCREEN = 2;
    } else if (screens.type == ES_RGIL_SCREEN_TYPE_STATISTIC) {
        GLOBAL_ES_RGIL_SCREEN_TYPE_SCREEN = 3;
    } else if (screens.type == ES_RGIL_SCREEN_TYPE_TICKET_SET) {
        GLOBAL_ES_RGIL_SCREEN_TYPE_SCREEN = 4;
    } else {
        GLOBAL_ES_RGIL_SCREEN_TYPE_SCREEN = 0;
    }
    ///----------------Dźwięki------------------------------------------------
    if (screens.sound_type == ES_RGIL_SOUND_CARD_OFF) {
        GLOBAL_ES_RGIL_SOUND_CARD = 0;
    } else if (screens.sound_type == ES_RGIL_SOUND_CARD_OK) {
        GLOBAL_ES_RGIL_SOUND_CARD = 1;
    } else if (screens.sound_type == ES_RGIL_SOUND_CARD_NG) {
        GLOBAL_ES_RGIL_SOUND_CARD = 2;
    } else if (screens.sound_type == ES_RGIL_SOUND_CARD_REDUCTION) {
        GLOBAL_ES_RGIL_SOUND_CARD = 3;
    } else if (screens.sound_type == ES_RGIL_SOUND_CARD_BLACK) {
        GLOBAL_ES_RGIL_SOUND_CARD = 4;
    }
    else {
        GLOBAL_ES_RGIL_SOUND_CARD = -1;
    }

    return LOGIN;
}
JNIEXPORT jint JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_VALIDATE(JNIEnv *env, jobject thiz, jobject iso_dep,
                                                    jbyteArray uid_num) {
    isoDepObject = iso_dep;
    jsize len;
    len = env->GetArrayLength(uid_num);
    auto *uidBytes = new uint8_t[len];
    env->GetByteArrayRegion(uid_num, 0, len, reinterpret_cast<jbyte *>(uidBytes));

    es_rgil_screens screens = {static_cast<es_rgil_screen_type>(0)};

    int32_t VALIDATE = rgil_entry(ES_RGIL_CMD_VALIDATE, uidBytes, len, &screens);

    ///----------------INSPEKTOR------------------------------------------------

    if (screens.screen.inspector_screen.is_msg == ES_RGIL_SCREEN_BOOL_TRUE) {
        screen_inspector_screen_is_msg = true;
    } else {
        screen_inspector_screen_is_msg = false;
    }
    message = screens.screen.inspector_screen.message;
    card_number = screens.screen.inspector_screen.card_number;
    deletion_date_time = screens.screen.inspector_screen.deletion_date_time;
    vehicle_no = screens.screen.inspector_screen.vehicle_no;
    line = screens.screen.inspector_screen.line;
    courseid = screens.screen.inspector_screen.courseid;
//    courseid_prev = screens.screen.inspector_screen.courseid_prev;
    stop_in = screens.screen.inspector_screen.stop_in;
//    stop_in_prev = screens.screen.inspector_screen.stop_in_prev;
    stop_out = screens.screen.inspector_screen.stop_out;
//    stop_out_prev = screens.screen.inspector_screen.stop_out_prev;
    zone_in = screens.screen.inspector_screen.zone_in;
//    zone_in_prev = screens.screen.inspector_screen.zone_in_prev;
    zone_out = screens.screen.inspector_screen.zone_out;
//    zone_out_prev = screens.screen.inspector_screen.zone_out_prev;
    stop_in_name = screens.screen.inspector_screen.stop_in_name;

    ///----------------Typ Ekranu------------------------------------------------
    if (screens.type == ES_RGIL_SCREEN_TYPE_MESSAGE) {
        GLOBAL_ES_RGIL_SCREEN_TYPE_SCREEN = 1;
    } else if (screens.type == ES_RGIL_SCREEN_TYPE_INSPECTOR) {
        GLOBAL_ES_RGIL_SCREEN_TYPE_SCREEN = 2;
    } else if (screens.type == ES_RGIL_SCREEN_TYPE_STATISTIC) {
        GLOBAL_ES_RGIL_SCREEN_TYPE_SCREEN = 3;
    } else if (screens.type == ES_RGIL_SCREEN_TYPE_TICKET_SET) {
        GLOBAL_ES_RGIL_SCREEN_TYPE_SCREEN = 4;
    } else {
        GLOBAL_ES_RGIL_SCREEN_TYPE_SCREEN = 0;
    }

    ///----------------Aktywne Ekrany------------------------------------------------
    if ((screens.screen.ticket_screen.what_is & ES_RGIL_SCREEN_TICKET_IS_EP) ==
        ES_RGIL_SCREEN_TICKET_IS_EP) {
        GLOBAL_ES_RGIL_SCREEN_TICKET_IS_EP = 0x01;
    } else {
        GLOBAL_ES_RGIL_SCREEN_TICKET_IS_EP = 0x00;
    }
    if ((screens.screen.ticket_screen.what_is & ES_RGIL_SCREEN_TICKET_IS_PERIODIC1) ==
        ES_RGIL_SCREEN_TICKET_IS_PERIODIC1) {
        GLOBAL_ES_RGIL_SCREEN_TICKET_IS_PERIODIC1 = 0x02;
    } else {
        GLOBAL_ES_RGIL_SCREEN_TICKET_IS_PERIODIC1 = 0x00;
    }
    if ((screens.screen.ticket_screen.what_is & ES_RGIL_SCREEN_TICKET_IS_PERIODIC2) ==
        ES_RGIL_SCREEN_TICKET_IS_PERIODIC2) {
        GLOBAL_ES_RGIL_SCREEN_TICKET_IS_PERIODIC2 = 0x04;
    } else {
        GLOBAL_ES_RGIL_SCREEN_TICKET_IS_PERIODIC2 = 0x00;
    }
    if ((screens.screen.ticket_screen.what_is & ES_RGIL_SCREEN_TICKET_IS_PERSON) ==
        ES_RGIL_SCREEN_TICKET_IS_PERSON) {
        GLOBAL_ES_RGIL_SCREEN_TICKET_IS_PERSON = 0x08;
    } else {
        GLOBAL_ES_RGIL_SCREEN_TICKET_IS_PERSON = 0x00;
    }
    GLOBAL_ES_RGIL_SCREEN_TICKET_IS = GLOBAL_ES_RGIL_SCREEN_TICKET_IS_EP+GLOBAL_ES_RGIL_SCREEN_TICKET_IS_PERIODIC1+GLOBAL_ES_RGIL_SCREEN_TICKET_IS_PERIODIC2+GLOBAL_ES_RGIL_SCREEN_TICKET_IS_PERSON
            ;

    ///----------------Ikony------------------------------------------------

    if ((screens.screen.ticket_screen.ticket_state.ep_icon & ES_RGIL_SCREEN_TICKET_ICON_OK) == ES_RGIL_SCREEN_TICKET_ICON_OK) {
        ticket_state_ep_icon = 1;
    } else if ((screens.screen.ticket_screen.ticket_state.ep_icon & ES_RGIL_SCREEN_TICKET_ICON_NG) == ES_RGIL_SCREEN_TICKET_ICON_NG) {
        ticket_state_ep_icon = 2;
    } else  if ((screens.screen.ticket_screen.ticket_state.ep_icon & ES_RGIL_SCREEN_TICKET_ICON_ABSENCE) == ES_RGIL_SCREEN_TICKET_ICON_ABSENCE) {
        ticket_state_ep_icon = 3;
    } else  if ((screens.screen.ticket_screen.ticket_state.ep_icon & ES_RGIL_SCREEN_TICKET_ICON_UCERTAIN) == ES_RGIL_SCREEN_TICKET_ICON_UCERTAIN) {
        ticket_state_ep_icon = 4;
    } else {
        ticket_state_ep_icon = 0;
    }

    ///----------------Dźwięki------------------------------------------------
    if (screens.sound_type == ES_RGIL_SOUND_CARD_OFF) {
        GLOBAL_ES_RGIL_SOUND_CARD = 0;
    } else if (screens.sound_type == ES_RGIL_SOUND_CARD_OK) {
        GLOBAL_ES_RGIL_SOUND_CARD = 1;
    } else if (screens.sound_type == ES_RGIL_SOUND_CARD_NG) {
        GLOBAL_ES_RGIL_SOUND_CARD = 2;
    } else if (screens.sound_type == ES_RGIL_SOUND_CARD_REDUCTION) {
        GLOBAL_ES_RGIL_SOUND_CARD = 3;
    } else if (screens.sound_type == ES_RGIL_SOUND_CARD_BLACK) {
        GLOBAL_ES_RGIL_SOUND_CARD = 4;
    }
    else {
        GLOBAL_ES_RGIL_SOUND_CARD = -1;
    }


    ///----------------Dane Personalne------------------------------------------------
    personal_data_name = (screens.screen.ticket_screen.personal_data.name);
    personal_data_passenger_type = (screens.screen.ticket_screen.personal_data.passenger_type);
    personal_data_amount_of_city_purse = (screens.screen.ticket_screen.personal_data.amount_of_city_purse);
    personal_data_reduction_type = (screens.screen.ticket_screen.personal_data.reduction_type);
    personal_data_reduction_range_validity = (screens.screen.ticket_screen.personal_data.reduction_range_validity);
    personal_data_reduction_document_number = (screens.screen.ticket_screen.personal_data.reduction_document_number);
    personal_data_reduction_document_type = (screens.screen.ticket_screen.personal_data.reduction_document_type);
    ///----------------Stan Biletu------------------------------------------------
    if (screens.screen.ticket_screen.ticket_state.is_msg == ES_RGIL_SCREEN_BOOL_TRUE) {
        ticket_screen_ticket_state_is_msg = true;
    } else {
        ticket_screen_ticket_state_is_msg = false;
    }
    ticket_state_header_background_color = (screens.screen.ticket_screen.ticket_state.header_background_color);
    ticket_state_header_font_color = (screens.screen.ticket_screen.ticket_state.header_font_color);
    ticket_state_card_number = (screens.screen.ticket_screen.ticket_state.card_number);
    ticket_state_message = (screens.screen.ticket_screen.ticket_state.message);
    ticket_state_msg_font_color = (screens.screen.ticket_screen.ticket_state.msg_font_color);
    ///----------------Portmonetka------------------------------------------------
    ticket_state_ep_control_result = (screens.screen.ticket_screen.ticket_state.ep_result.result);
    ticket_state_ep_control_result_background_color = (screens.screen.ticket_screen.ticket_state.ep_result.result_background_color);
    ticket_state_ep_number_of_tickets = (screens.screen.ticket_screen.ticket_state.ep_number_of_tickets);
    ticket_state_ep_number_of_tickets_font_color = (screens.screen.ticket_screen.ticket_state.ep_number_of_tickets_font_color);

    if (screens.screen.ticket_screen.ticket_state.display_expiration == ES_RGIL_SCREEN_BOOL_TRUE) {
        ticket_state_ep_display_expiration = true;
    } else {
        ticket_state_ep_display_expiration = false;
    }
    ticket_state_ep_expiration_date_time = (screens.screen.ticket_screen.ticket_state.expiration_date_time);
    ticket_state_ep_expiration_date_time_font_color = (screens.screen.ticket_screen.ticket_state.expiration_date_time_font_color);
    ticket_state_ep_expiration_zone = (screens.screen.ticket_screen.ticket_state.expiration_zone);
    ticket_state_ep_ng_info = (screens.screen.ticket_screen.ticket_state.ep_ng_info);
    ticket_state_ep_ng_info_font_color = (screens.screen.ticket_screen.ticket_state.ep_ng_info_font_color);
///----------------Okresowy 1------------------------------------------------
    ticket_state_periodic1_control_result = (screens.screen.ticket_screen.ticket_state.periodic1_result.result);
    ticket_state_periodic1_control_result_background_color = (screens.screen.ticket_screen.ticket_state.periodic1_result.result_background_color);
    ticket_state_periodic1_stop_name_from = (screens.screen.ticket_screen.ticket_state.periodic1_stop_name_from);
    ticket_state_periodic1_contract = (screens.screen.ticket_screen.ticket_state.periodic1_contract);
///----------------Okresowy 2------------------------------------------------
    ticket_state_periodic2_control_result = (screens.screen.ticket_screen.ticket_state.periodic2_result.result);
    ticket_state_periodic2_control_result_background_color = (screens.screen.ticket_screen.ticket_state.periodic2_result.result_background_color);
    ticket_state_periodic2_stop_name_from = (screens.screen.ticket_screen.ticket_state.periodic2_stop_name_from);
    ticket_state_periodic2_contract = (screens.screen.ticket_screen.ticket_state.periodic2_contract);

    ///----------------Bilety------------------------------------------------
///Portmonetka
    ticket_screen_ep_data_validity_date = (screens.screen.ticket_screen.ep_data.validity_date);
    ticket_screen_ep_data_validity_date_font_color = (screens.screen.ticket_screen.ep_data.validity_date_font_color);
    ticket_screen_ep_data_tarrif = (screens.screen.ticket_screen.ep_data.tarrif);
    ticket_screen_ep_data_amount_of_purse = (screens.screen.ticket_screen.ep_data.amount_of_purse);
    ticket_screen_ep_data_number_of_tickets = (screens.screen.ticket_screen.ep_data.number_of_tickets);
    ticket_screen_ep_data_expiration_date_time = (screens.screen.ticket_screen.ep_data.expiration_date_time);
    ticket_screen_ep_data_expiration_date_time_font_color = (screens.screen.ticket_screen.ep_data.expiration_date_time_font_color);
    ticket_screen_ep_data_expiration_zone = (screens.screen.ticket_screen.ep_data.expiration_zone);
    ticket_screen_ep_data_deletion_date_time = (screens.screen.ticket_screen.ep_data.deletion_date_time);
    ticket_screen_ep_data_deletion_date_time_font_color = (screens.screen.ticket_screen.ep_data.deletion_date_time_font_color);
    ticket_screen_ep_data_courseid = (screens.screen.ticket_screen.ep_data.courseid);
    ticket_screen_ep_data_courseid_font_color = (screens.screen.ticket_screen.ep_data.courseid_font_color);
    ticket_screen_ep_data_deletion_type = (screens.screen.ticket_screen.ep_data.deletion_type);
    ticket_screen_ep_data_deletion_type_font_color = (screens.screen.ticket_screen.ep_data.deletion_date_time_font_color);
    ticket_screen_ep_data_stop = (screens.screen.ticket_screen.ep_data.stop);
    ticket_screen_ep_data_stop_font_color = (screens.screen.ticket_screen.ep_data.stop_font_color);
    ticket_screen_ep_data_line = (screens.screen.ticket_screen.ep_data.line);
    ticket_screen_ep_data_line_font_color = (screens.screen.ticket_screen.ep_data.line_font_color);
///Okresowy 1
    if (screens.screen.ticket_screen.periodic_data_1.display_deletion == ES_RGIL_SCREEN_BOOL_TRUE){
        ticket_screen_periodic_data_1_display_deletion  = true;
    }else{
        ticket_screen_periodic_data_1_display_deletion = false;
    }
    ticket_screen_periodic_data_1_contract_symbol = (screens.screen.ticket_screen.periodic_data_1.contract_symbol);
    ticket_screen_periodic_data_1_range_validity = (screens.screen.ticket_screen.periodic_data_1.range_validity);
    ticket_screen_periodic_data_1_range_validity_font_color = (screens.screen.ticket_screen.periodic_data_1.range_validity_font_color);
    ticket_screen_periodic_data_1_days_of_week = (screens.screen.ticket_screen.periodic_data_1.days_of_week);
    ticket_screen_periodic_data_1_days_of_week_font_color = (screens.screen.ticket_screen.periodic_data_1.days_of_week_font_color);
    ticket_screen_periodic_data_1_lines = (screens.screen.ticket_screen.periodic_data_1.lines);
    ticket_screen_periodic_data_1_lines_font_color = (screens.screen.ticket_screen.periodic_data_1.lines_font_color);
    ticket_screen_periodic_data_1_zones = (screens.screen.ticket_screen.periodic_data_1.zones);
    ticket_screen_periodic_data_1_zones_font_color = (screens.screen.ticket_screen.periodic_data_1.zones_font_color);
    ticket_screen_periodic_data_1_deletion_date_time = (screens.screen.ticket_screen.periodic_data_1.deletion_date_time);
    ticket_screen_periodic_data_1_deletion_date_time_font_color = (screens.screen.ticket_screen.periodic_data_1.deletion_date_time_font_color);
    ticket_screen_periodic_data_1_courseid = (screens.screen.ticket_screen.periodic_data_1.courseid);
    ticket_screen_periodic_data_1_courseid_font_color = (screens.screen.ticket_screen.periodic_data_1.courseid_font_color);
    ticket_screen_periodic_data_1_deletion_type = (screens.screen.ticket_screen.periodic_data_1.deletion_type);
    ticket_screen_periodic_data_1_deletion_type_font_color = (screens.screen.ticket_screen.periodic_data_1.deletion_type_font_color);
    ticket_screen_periodic_data_1_stop = (screens.screen.ticket_screen.periodic_data_1.stop);
    ticket_screen_periodic_data_1_stop_font_color = (screens.screen.ticket_screen.periodic_data_1.stop_font_color);
    ticket_screen_periodic_data_1_zone = (screens.screen.ticket_screen.periodic_data_1.zone);
    ticket_screen_periodic_data_1_zone_font_color = (screens.screen.ticket_screen.periodic_data_1.zone_font_color);
    ticket_screen_periodic_data_1_line = (screens.screen.ticket_screen.periodic_data_1.line);
    ticket_screen_periodic_data_1_line_font_color = (screens.screen.ticket_screen.periodic_data_1.line_font_color);
///Okresowy 2
    if (screens.screen.ticket_screen.periodic_data_2.display_deletion == ES_RGIL_SCREEN_BOOL_TRUE){
        ticket_screen_periodic_data_2_display_deletion = true;
    }else{
        ticket_screen_periodic_data_2_display_deletion = false;
    }
    ticket_screen_periodic_data_2_contract_symbol = (screens.screen.ticket_screen.periodic_data_2.contract_symbol);
    ticket_screen_periodic_data_2_range_validity = (screens.screen.ticket_screen.periodic_data_2.range_validity);
    ticket_screen_periodic_data_2_range_validity_font_color = (screens.screen.ticket_screen.periodic_data_2.range_validity_font_color);
    ticket_screen_periodic_data_2_days_of_week = (screens.screen.ticket_screen.periodic_data_2.days_of_week);
    ticket_screen_periodic_data_2_days_of_week_font_color = (screens.screen.ticket_screen.periodic_data_2.days_of_week_font_color);
    ticket_screen_periodic_data_2_lines = (screens.screen.ticket_screen.periodic_data_2.lines);
    ticket_screen_periodic_data_2_lines_font_color = (screens.screen.ticket_screen.periodic_data_2.lines_font_color);
    ticket_screen_periodic_data_2_zones = (screens.screen.ticket_screen.periodic_data_2.zones);
    ticket_screen_periodic_data_2_zones_font_color = (screens.screen.ticket_screen.periodic_data_2.zones_font_color);
    ticket_screen_periodic_data_2_deletion_date_time = (screens.screen.ticket_screen.periodic_data_2.deletion_date_time);
    ticket_screen_periodic_data_2_deletion_date_time_font_color = (screens.screen.ticket_screen.periodic_data_2.deletion_date_time_font_color);
    ticket_screen_periodic_data_2_courseid = (screens.screen.ticket_screen.periodic_data_2.courseid);
    ticket_screen_periodic_data_2_courseid_font_color = (screens.screen.ticket_screen.periodic_data_2.courseid_font_color);
    ticket_screen_periodic_data_2_deletion_type = (screens.screen.ticket_screen.periodic_data_2.deletion_type);
    ticket_screen_periodic_data_2_deletion_type_font_color = (screens.screen.ticket_screen.periodic_data_2.deletion_type_font_color);
    ticket_screen_periodic_data_2_stop = (screens.screen.ticket_screen.periodic_data_2.stop);
    ticket_screen_periodic_data_2_stop_font_color = (screens.screen.ticket_screen.periodic_data_2.stop_font_color);
    ticket_screen_periodic_data_2_zone = (screens.screen.ticket_screen.periodic_data_2.zone);
    ticket_screen_periodic_data_2_zone_font_color = (screens.screen.ticket_screen.periodic_data_2.zone_font_color);
    ticket_screen_periodic_data_2_line = (screens.screen.ticket_screen.periodic_data_2.line);
    ticket_screen_periodic_data_2_line_font_color = (screens.screen.ticket_screen.periodic_data_2.lines_font_color);

    message_screen_msg = (screens.screen.message_screen.msg);

    return VALIDATE;
}
JNIEXPORT jint JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_RELOAD(JNIEnv *env, jobject thiz) {
    es_rgil_screens screens = {static_cast<es_rgil_screen_type>(0)};

    int32_t RELOAD = rgil_entry(ES_RGIL_CMD_RELOAD, NULL, 0, NULL);

    message_screen_msg = (screens.screen.message_screen.msg);

    return RELOAD;
}
JNIEXPORT jint JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_IDLE(JNIEnv *env, jobject thiz) {

    es_rgil_screens screens = {static_cast<es_rgil_screen_type>(0)};
    int32_t IDLE = rgil_entry(ES_RGIL_CMD_IDLE, NULL, 0, &screens);


    tickets = screens.screen.statistic_screen.tickets;
    inspections = screens.screen.statistic_screen.inspections;
    bank_cards = screens.screen.statistic_screen.bank_cards;
    reports = screens.screen.statistic_screen.reports;

    message_screen_msg = (screens.screen.message_screen.msg);

    ///----------------Typ Ekranu------------------------------------------------
    if (screens.type == ES_RGIL_SCREEN_TYPE_MESSAGE) {
        GLOBAL_ES_RGIL_SCREEN_TYPE_SCREEN = 1;
    } else if (screens.type == ES_RGIL_SCREEN_TYPE_INSPECTOR) {
        GLOBAL_ES_RGIL_SCREEN_TYPE_SCREEN = 2;
    } else if (screens.type == ES_RGIL_SCREEN_TYPE_STATISTIC) {
        GLOBAL_ES_RGIL_SCREEN_TYPE_SCREEN = 3;
    } else if (screens.type == ES_RGIL_SCREEN_TYPE_TICKET_SET) {
        GLOBAL_ES_RGIL_SCREEN_TYPE_SCREEN = 4;
    } else {
        GLOBAL_ES_RGIL_SCREEN_TYPE_SCREEN = 0;
    }
    ///----------------Dźwięki------------------------------------------------
    if (screens.sound_type == ES_RGIL_SOUND_CARD_OFF) {
        GLOBAL_ES_RGIL_SOUND_CARD = 0;
    } else if (screens.sound_type == ES_RGIL_SOUND_CARD_OK) {
        GLOBAL_ES_RGIL_SOUND_CARD = 1;
    } else if (screens.sound_type == ES_RGIL_SOUND_CARD_NG) {
        GLOBAL_ES_RGIL_SOUND_CARD = 2;
    } else if (screens.sound_type == ES_RGIL_SOUND_CARD_REDUCTION) {
        GLOBAL_ES_RGIL_SOUND_CARD = 3;
    } else if (screens.sound_type == ES_RGIL_SOUND_CARD_BLACK) {
        GLOBAL_ES_RGIL_SOUND_CARD = 4;
    }
    else {
        GLOBAL_ES_RGIL_SOUND_CARD = -1;
    }


    return IDLE;
}
//JNIEXPORT jint JNICALL
//Java_com_rg_sprawdzarka_libraryjni_Library_LOCK(JNIEnv *env, jobject thiz, jobject iso_dep,
//                                                jbyteArray uid_num) {
//    isoDepObject = iso_dep;
//    jsize len;
//    len = env->GetArrayLength(uid_num);
//    auto *uidBytes = new uint8_t[len];
//    env->GetByteArrayRegion(uid_num, 0, len, reinterpret_cast<jbyte *>(uidBytes));
//
//    es_rgil_screens screens = {static_cast<es_rgil_screen_type>(0)};
//
//    int32_t LOCK = rgil_entry(ES_RGIL_CMD_LOCK, uidBytes, len, &screens);
//
//    message_screen_msg = (screens.screen.message_screen.msg);
//
//
//    ///----------------Typ Ekranu------------------------------------------------
//    if (screens.type == ES_RGIL_SCREEN_TYPE_MESSAGE) {
//        GLOBAL_ES_RGIL_SCREEN_TYPE_SCREEN = 1;
//    } else if (screens.type == ES_RGIL_SCREEN_TYPE_INSPECTOR) {
//        GLOBAL_ES_RGIL_SCREEN_TYPE_SCREEN = 2;
//    } else if (screens.type == ES_RGIL_SCREEN_TYPE_STATISTIC) {
//        GLOBAL_ES_RGIL_SCREEN_TYPE_SCREEN = 3;
//    } else if (screens.type == ES_RGIL_SCREEN_TYPE_TICKET_SET) {
//        GLOBAL_ES_RGIL_SCREEN_TYPE_SCREEN = 4;
//    } else {
//        GLOBAL_ES_RGIL_SCREEN_TYPE_SCREEN = 0;
//    }
//    ///----------------Dźwięki------------------------------------------------
//    if (screens.sound_type == ES_RGIL_SOUND_CARD_OFF) {
//        GLOBAL_ES_RGIL_SOUND_CARD = 0;
//    } else if (screens.type == ES_RGIL_SOUND_CARD_OK) {
//        GLOBAL_ES_RGIL_SOUND_CARD = 1;
//    } else if (screens.type == ES_RGIL_SOUND_CARD_NG) {
//        GLOBAL_ES_RGIL_SOUND_CARD = 2;
//    } else if (screens.type == ES_RGIL_SOUND_CARD_REDUCTION) {
//        GLOBAL_ES_RGIL_SOUND_CARD = 3;
//    } else {
//        GLOBAL_ES_RGIL_SOUND_CARD = 4;
//    }
//
//    return LOCK;
//}
//JNIEXPORT jint JNICALL
//Java_com_rg_sprawdzarka_libraryjni_Library_UNLOCK(JNIEnv *env, jobject thiz, jobject iso_dep,
//                                                  jbyteArray uid_num) {
//    isoDepObject = iso_dep;
//    jsize len;
//    len = env->GetArrayLength(uid_num);
//    auto *uidBytes = new uint8_t[len];
//    env->GetByteArrayRegion(uid_num, 0, len, reinterpret_cast<jbyte *>(uidBytes));
//
//    es_rgil_screens screens = {static_cast<es_rgil_screen_type>(0)};
//
//
//    int32_t UNLOCK = rgil_entry(ES_RGIL_CMD_UNLOCK, uidBytes, len, &screens);
//
//    message_screen_msg = (screens.screen.message_screen.msg);
//
//    ///----------------Typ Ekranu------------------------------------------------
//    if (screens.type == ES_RGIL_SCREEN_TYPE_MESSAGE) {
//        GLOBAL_ES_RGIL_SCREEN_TYPE_SCREEN = 1;
//    } else if (screens.type == ES_RGIL_SCREEN_TYPE_INSPECTOR) {
//        GLOBAL_ES_RGIL_SCREEN_TYPE_SCREEN = 2;
//    } else if (screens.type == ES_RGIL_SCREEN_TYPE_STATISTIC) {
//        GLOBAL_ES_RGIL_SCREEN_TYPE_SCREEN = 3;
//    } else if (screens.type == ES_RGIL_SCREEN_TYPE_TICKET_SET) {
//        GLOBAL_ES_RGIL_SCREEN_TYPE_SCREEN = 4;
//    } else {
//        GLOBAL_ES_RGIL_SCREEN_TYPE_SCREEN = 0;
//    }
//    ///----------------Dźwięki------------------------------------------------
//    if (screens.sound_type == ES_RGIL_SOUND_CARD_OFF) {
//        GLOBAL_ES_RGIL_SOUND_CARD = 0;
//    } else if (screens.type == ES_RGIL_SOUND_CARD_OK) {
//        GLOBAL_ES_RGIL_SOUND_CARD = 1;
//    } else if (screens.type == ES_RGIL_SOUND_CARD_NG) {
//        GLOBAL_ES_RGIL_SOUND_CARD = 2;
//    } else if (screens.type == ES_RGIL_SOUND_CARD_REDUCTION) {
//        GLOBAL_ES_RGIL_SOUND_CARD = 3;
//    } else {
//        GLOBAL_ES_RGIL_SOUND_CARD = 4;
//    }
//
//    return UNLOCK;
//}
JNIEXPORT jint JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_CHECK(JNIEnv *env, jobject thiz, jobject iso_dep,
                                                 jbyteArray uid_num) {
    isoDepObject = iso_dep;
    jsize len;
    len = env->GetArrayLength(uid_num);
    auto *uidBytes = new uint8_t[len];
    env->GetByteArrayRegion(uid_num, 0, len, reinterpret_cast<jbyte *>(uidBytes));

    es_rgil_screens screens = {static_cast<es_rgil_screen_type>(0)};

    int32_t CHECK = rgil_entry(ES_RGIL_CMD_CHECK, uidBytes, len, &screens);

    message_screen_msg = (screens.screen.message_screen.msg);


    ///----------------Typ Ekranu------------------------------------------------
    if (screens.type == ES_RGIL_SCREEN_TYPE_MESSAGE) {
        GLOBAL_ES_RGIL_SCREEN_TYPE_SCREEN = 1;
    } else if (screens.type == ES_RGIL_SCREEN_TYPE_INSPECTOR) {
        GLOBAL_ES_RGIL_SCREEN_TYPE_SCREEN = 2;
    } else if (screens.type == ES_RGIL_SCREEN_TYPE_STATISTIC) {
        GLOBAL_ES_RGIL_SCREEN_TYPE_SCREEN = 3;
    } else if (screens.type == ES_RGIL_SCREEN_TYPE_TICKET_SET) {
        GLOBAL_ES_RGIL_SCREEN_TYPE_SCREEN = 4;
    } else {
        GLOBAL_ES_RGIL_SCREEN_TYPE_SCREEN = 0;
    }
    ///----------------Dźwięki------------------------------------------------
    if (screens.sound_type == ES_RGIL_SOUND_CARD_OFF) {
        GLOBAL_ES_RGIL_SOUND_CARD = 0;
    } else if (screens.sound_type == ES_RGIL_SOUND_CARD_OK) {
        GLOBAL_ES_RGIL_SOUND_CARD = 1;
    } else if (screens.sound_type == ES_RGIL_SOUND_CARD_NG) {
        GLOBAL_ES_RGIL_SOUND_CARD = 2;
    } else if (screens.sound_type == ES_RGIL_SOUND_CARD_REDUCTION) {
        GLOBAL_ES_RGIL_SOUND_CARD = 3;
    } else if (screens.sound_type == ES_RGIL_SOUND_CARD_BLACK) {
        GLOBAL_ES_RGIL_SOUND_CARD = 4;
    }
    else {
        GLOBAL_ES_RGIL_SOUND_CARD = -1;
    }

    return CHECK;
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_message_1screen_1msg(JNIEnv *env, jobject thiz) {
    return createJniString(env, message_screen_msg);
}
JNIEXPORT jint JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_checker_1ID_1screen(JNIEnv *env, jobject thiz) {
    return createJniInt(env, checkerID);
}
JNIEXPORT jint JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_soft_1ID_1screen(JNIEnv *env, jobject thiz) {
    return createJniInt(env, softID);
}
///Dane inspektora
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_inspector_1screen_1message(JNIEnv *env,
                                                                      jobject thiz) {
    return createJniString(env, message);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_inspector_1screen_1card_1number(JNIEnv *env,
                                                                           jobject thiz) {
    return createJniString(env, card_number);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_inspector_1screen_1deletion_1date_1time(JNIEnv *env,
                                                                                   jobject thiz) {
    return createJniString(env, deletion_date_time);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_inspector_1screen_1vehicle_1no(JNIEnv *env,
                                                                          jobject thiz) {
    return createJniString(env, vehicle_no);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_inspector_1screen_1line(JNIEnv *env, jobject thiz) {
    return createJniString(env, line);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_inspector_1screen_1courseid(JNIEnv *env,
                                                                       jobject thiz) {
    return createJniString(env, courseid);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_inspector_1screen_1courseid_1prev(JNIEnv *env,
                                                                             jobject thiz) {
    return createJniString(env, courseid);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_inspector_1screen_1stop_1in(JNIEnv *env,
                                                                       jobject thiz) {
    return createJniString(env, stop_in);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_inspector_1screen_1stop_1in_1prev(JNIEnv *env,
                                                                             jobject thiz) {
    return createJniString(env, stop_in_prev);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_inspector_1screen_1stop_1out(JNIEnv *env,
                                                                        jobject thiz) {
    return createJniString(env, stop_out);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_inspector_1screen_1stop_1out_1prev(JNIEnv *env,
                                                                              jobject thiz) {
    return createJniString(env, stop_out_prev);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_inspector_1screen_1zone_1in(JNIEnv *env,
                                                                       jobject thiz) {
    return createJniString(env, zone_in);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_inspector_1screen_1zone_1in_1prev(JNIEnv *env,
                                                                             jobject thiz) {
    return createJniString(env, zone_in_prev);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_inspector_1screen_1zone_1out(JNIEnv *env,
                                                                        jobject thiz) {
    return createJniString(env, zone_out);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_inspector_1screen_1zone_1out_1prev(JNIEnv *env,
                                                                              jobject thiz) {
    return createJniString(env, zone_out_prev);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_inspector_1screen_1stop_1in_1name(JNIEnv *env,
                                                                             jobject thiz) {
    return createJniString(env, stop_in_name);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_statistic_1screen_1tickets(JNIEnv *env,
                                                                      jobject thiz) {
    return createJniString(env, tickets);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_statistic_1screen_1inspections(JNIEnv *env,
                                                                          jobject thiz) {
    return createJniString(env, inspections);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_statistic_1screen_1bank_1cards(JNIEnv *env,
                                                                          jobject thiz) {
    return createJniString(env, bank_cards);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_statistic_1screen_1reports(JNIEnv *env,
                                                                      jobject thiz) {
    return createJniString(env, reports);
}
///Dane personalne
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_personal_1data_1name(JNIEnv *env, jobject thiz) {
    return createJniString(env, personal_data_name);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_personal_1data_1passenger_1type(JNIEnv *env,
                                                                           jobject thiz) {
    return createJniString(env, personal_data_passenger_type);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_personal_1data_1amount_1of_1city_1purse(JNIEnv *env,
                                                                                   jobject thiz) {
    return createJniString(env, personal_data_amount_of_city_purse);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_personal_1data_1reduction_1type(JNIEnv *env,
                                                                           jobject thiz) {
    return createJniString(env, personal_data_reduction_type);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_personal_1data_1reduction_1range_1validity(
        JNIEnv *env, jobject thiz) {
    return createJniString(env, personal_data_reduction_range_validity);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_personal_1data_1reduction_1document_1number(
        JNIEnv *env, jobject thiz) {
    return createJniString(env, personal_data_reduction_document_number);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_personal_1data_1reduction_1document_1type(JNIEnv *env,
                                                                                     jobject thiz) {
    return createJniString(env, personal_data_reduction_document_type);
}
///Stan biletu
JNIEXPORT jlong JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1state_1header_1background_1color(JNIEnv *env,
                                                                                    jobject thiz) {
    return createJniLong(env, ticket_state_header_background_color);
}
JNIEXPORT jlong JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1state_1header_1font_1color(JNIEnv *env,
                                                                              jobject thiz) {
    return createJniLong(env, ticket_state_header_font_color);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1state_1card_1number(JNIEnv *env,
                                                                       jobject thiz) {
    return createJniString(env, ticket_state_card_number);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1state_1message(JNIEnv *env, jobject thiz) {
    return createJniString(env, ticket_state_message);
}
JNIEXPORT jlong JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1state_1msg_1font_1color(JNIEnv *env,
                                                                           jobject thiz) {
    return createJniLong(env, ticket_state_msg_font_color);
}
///Portmonetka
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1state_1ep_1control_1result(JNIEnv *env,
                                                                              jobject thiz) {
    return createJniString(env, ticket_state_ep_control_result);
}
JNIEXPORT jlong JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1state_1ep_1control_1result_1background_1color(
        JNIEnv *env, jobject thiz) {
    return createJniLong(env, ticket_state_ep_control_result_background_color);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1state_1ep_1number_1of_1tickets(JNIEnv *env,
                                                                                  jobject thiz) {
    return createJniString(env, ticket_state_ep_number_of_tickets);
}
JNIEXPORT jlong JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1state_1ep_1number_1of_1tickets_1font_1color(
        JNIEnv *env, jobject thiz) {
    return createJniLong(env, ticket_state_ep_number_of_tickets_font_color);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1state_1ep_1expiration_1date_1time(JNIEnv *env,
                                                                                     jobject thiz) {
    return createJniString(env, ticket_state_ep_expiration_date_time);
}
JNIEXPORT jlong JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1state_1ep_1expiration_1date_1time_1font_1color(
        JNIEnv *env, jobject thiz) {
    return createJniLong(env, ticket_state_ep_expiration_date_time_font_color);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1state_1ep_1expiration_1zone(JNIEnv *env,
                                                                               jobject thiz) {
    return createJniString(env, ticket_state_ep_expiration_zone);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1state_1ep_1ng_1info(JNIEnv *env,
                                                                       jobject thiz) {
    return createJniString(env, ticket_state_ep_ng_info);
}
JNIEXPORT jlong JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1state_1ep_1ng_1info_1font_1color(JNIEnv *env,
                                                                                    jobject thiz) {
    return createJniLong(env, ticket_state_ep_ng_info_font_color);
}
///Okresowy 1
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1state_1periodic1_1control_1result(JNIEnv *env,
                                                                                     jobject thiz) {
    return createJniString(env, ticket_state_periodic1_control_result);
}
JNIEXPORT jlong JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1state_1periodic1_1control_1result_1background_1color__(
        JNIEnv *env, jobject thiz) {
    return createJniLong(env, ticket_state_periodic1_control_result_background_color);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1state_1periodic1_1stop_1name_1from(
        JNIEnv *env, jobject thiz) {
    return createJniString(env, ticket_state_periodic1_stop_name_from);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1state_1periodic1_1contract(JNIEnv *env,
                                                                              jobject thiz) {
    return createJniString(env, ticket_state_periodic1_contract);
}
///Okresowy 2
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1state_1periodic2_1control_1result(JNIEnv *env,
                                                                                     jobject thiz) {
    return createJniString(env, ticket_state_periodic2_control_result);
}
JNIEXPORT jlong JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1state_1periodic2_1control_1result_1background_1color(
        JNIEnv *env, jobject thiz) {
    return createJniLong(env, ticket_state_periodic2_control_result_background_color);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1state_1periodic2_1stop_1name_1from(
        JNIEnv *env, jobject thiz) {
    return createJniString(env, ticket_state_periodic2_stop_name_from);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1state_1periodic2_1contract(JNIEnv *env,
                                                                              jobject thiz) {
    return createJniString(env, ticket_state_periodic2_contract);
}
//Portmonetka - szczegóły
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1ep_1data_1validity_1date(JNIEnv *env,
                                                                                    jobject thiz) {
    return createJniString(env, ticket_screen_ep_data_validity_date);
}
JNIEXPORT jlong JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1ep_1data_1validity_1date_1font_1color(
        JNIEnv *env, jobject thiz) {
    return createJniLong(env, ticket_screen_ep_data_validity_date_font_color);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1ep_1data_1tarrif(JNIEnv *env,
                                                                            jobject thiz) {
    return createJniString(env, ticket_screen_ep_data_tarrif);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1ep_1data_1amount_1of_1purse(
        JNIEnv *env, jobject thiz) {
    return createJniString(env, ticket_screen_ep_data_amount_of_purse);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1ep_1data_1number_1of_1tickets(
        JNIEnv *env, jobject thiz) {
    return createJniString(env, ticket_screen_ep_data_number_of_tickets);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1ep_1data_1expiration_1date_1time(
        JNIEnv *env, jobject thiz) {
    return createJniString(env, ticket_screen_ep_data_expiration_date_time);
}
JNIEXPORT jlong JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1ep_1data_1expiration_1date_1time_1font_1color(
        JNIEnv *env, jobject thiz) {
    return createJniLong(env, ticket_screen_ep_data_expiration_date_time_font_color);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1ep_1data_1expiration_1zone(
        JNIEnv *env, jobject thiz) {
    return createJniString(env, ticket_screen_ep_data_expiration_zone);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1ep_1data_1deletion_1date_1time(
        JNIEnv *env, jobject thiz) {
    return createJniString(env, ticket_screen_ep_data_deletion_date_time);
}
JNIEXPORT jlong JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1ep_1data_1deletion_1date_1time_1font_1color(
        JNIEnv *env, jobject thiz) {
    return createJniLong(env, ticket_screen_ep_data_deletion_date_time_font_color);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1ep_1data_1courseid(JNIEnv *env,
                                                                              jobject thiz) {
    return createJniString(env, ticket_screen_ep_data_courseid);
}
JNIEXPORT jlong JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1ep_1data_1courseid_1font_1color(
        JNIEnv *env, jobject thiz) {
    return createJniLong(env, ticket_screen_ep_data_courseid_font_color);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1ep_1data_1deletion_1type(JNIEnv *env,
                                                                                    jobject thiz) {
    return createJniString(env, ticket_screen_ep_data_deletion_type);
}
JNIEXPORT jlong JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1ep_1data_1deletion_1type_1font_1color(
        JNIEnv *env, jobject thiz) {
    return createJniLong(env, ticket_screen_ep_data_deletion_type_font_color);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1ep_1data_1stop(JNIEnv *env,
                                                                          jobject thiz) {
    return createJniString(env, ticket_screen_ep_data_stop);
}
JNIEXPORT jlong JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1ep_1data_1stop_1font_1color(
        JNIEnv *env, jobject thiz) {
    return createJniLong(env, ticket_screen_ep_data_stop_font_color);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1ep_1data_1line(JNIEnv *env,
                                                                          jobject thiz) {
    return createJniString(env, ticket_screen_ep_data_line);
}
JNIEXPORT jlong JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1ep_1data_1line_1font_1color(
        JNIEnv *env, jobject thiz) {
    return createJniLong(env, ticket_screen_ep_data_line_font_color);
}
JNIEXPORT jboolean JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_11_1display_1deletion(
        JNIEnv *env, jobject thiz) {
    return ticket_screen_periodic_data_1_display_deletion;
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_11_1contract_1symbol(
        JNIEnv *env, jobject thiz) {
    return createJniString(env, ticket_screen_periodic_data_1_contract_symbol);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_11_1range_1validity(
        JNIEnv *env, jobject thiz) {
    return createJniString(env, ticket_screen_periodic_data_1_range_validity);
}
JNIEXPORT jlong JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_11_1range_1validity_1font_1color(
        JNIEnv *env, jobject thiz) {
    return createJniLong(env, ticket_screen_periodic_data_1_range_validity_font_color);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_11_1days_1of_1week(
        JNIEnv *env, jobject thiz) {
    return createJniString(env, ticket_screen_periodic_data_1_days_of_week);
}
JNIEXPORT jlong JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_11_1days_1of_1week_1font_1color(
        JNIEnv *env, jobject thiz) {
    return createJniLong(env, ticket_screen_periodic_data_1_days_of_week_font_color);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_11_1lines(JNIEnv *env,
                                                                                    jobject thiz) {
    return createJniString(env, ticket_screen_periodic_data_1_lines);
}
JNIEXPORT jlong JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_11_1lines_1font_1color(
        JNIEnv *env, jobject thiz) {
    return createJniLong(env, ticket_screen_periodic_data_1_lines_font_color);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_11_1zones(JNIEnv *env,
                                                                                    jobject thiz) {
    return createJniString(env, ticket_screen_periodic_data_1_zones);
}
JNIEXPORT jlong JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_11_1zones_1font_1color(
        JNIEnv *env, jobject thiz) {
    return createJniLong(env, ticket_screen_periodic_data_1_zones_font_color);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_11_1deletion_1date_1time(
        JNIEnv *env, jobject thiz) {
    return createJniString(env, ticket_screen_periodic_data_1_deletion_date_time);
}
JNIEXPORT jlong JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_11_1deletion_1date_1time_1font_1color(
        JNIEnv *env, jobject thiz) {
    return createJniLong(env, ticket_screen_periodic_data_1_deletion_date_time_font_color);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_11_1courseid(
        JNIEnv *env, jobject thiz) {
    return createJniString(env, ticket_screen_periodic_data_1_courseid);
}
JNIEXPORT jlong JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_11_1courseid_1font_1color(
        JNIEnv *env, jobject thiz) {
    return createJniLong(env, ticket_screen_periodic_data_1_courseid_font_color);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_11_1deletion_1type(
        JNIEnv *env, jobject thiz) {
    return createJniString(env, ticket_screen_periodic_data_1_deletion_type);
}
JNIEXPORT jlong JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_11_1deletion_1type_1font_1color(
        JNIEnv *env, jobject thiz) {
    return createJniLong(env, ticket_screen_periodic_data_1_deletion_type_font_color);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_11_1stop(JNIEnv *env,
                                                                                   jobject thiz) {
    return createJniString(env, ticket_screen_periodic_data_1_stop);
}
JNIEXPORT jlong JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_11_1stop_1font_1color(
        JNIEnv *env, jobject thiz) {
    return createJniLong(env, ticket_screen_periodic_data_1_stop_font_color);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_11_1zone(JNIEnv *env,
                                                                                   jobject thiz) {
    return createJniString(env, ticket_screen_periodic_data_1_zone);
}
JNIEXPORT jlong JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_11_1zone_1font_1color(
        JNIEnv *env, jobject thiz) {
    return createJniLong(env, ticket_screen_periodic_data_1_zone_font_color);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_11_1line(JNIEnv *env,
                                                                                   jobject thiz) {
    return createJniString(env, ticket_screen_periodic_data_1_line);
}
JNIEXPORT jlong JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_11_1line_1font_1color(
        JNIEnv *env, jobject thiz) {
    return createJniLong(env, ticket_screen_periodic_data_1_line_font_color);
}
JNIEXPORT jboolean JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_12_1display_1deletion(
        JNIEnv *env, jobject thiz) {
    return ticket_screen_periodic_data_2_display_deletion;
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_12_1contract_1symbol(
        JNIEnv *env, jobject thiz) {
    return createJniString(env, ticket_screen_periodic_data_2_contract_symbol);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_12_1range_1validity(
        JNIEnv *env, jobject thiz) {
    return createJniString(env, ticket_screen_periodic_data_2_range_validity);
}
JNIEXPORT jlong JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_12_1range_1validity_1font_1color(
        JNIEnv *env, jobject thiz) {
    return createJniLong(env, ticket_screen_periodic_data_2_range_validity_font_color);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_12_1days_1of_1week(
        JNIEnv *env, jobject thiz) {
    return createJniString(env, ticket_screen_periodic_data_2_days_of_week);
}
JNIEXPORT jlong JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_12_1days_1of_1week_1font_1color(
        JNIEnv *env, jobject thiz) {
    return createJniLong(env, ticket_screen_periodic_data_2_days_of_week_font_color);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_12_1lines(JNIEnv *env,
                                                                                    jobject thiz) {
    return createJniString(env, ticket_screen_periodic_data_2_lines);
}
JNIEXPORT jlong JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_12_1lines_1font_1color(
        JNIEnv *env, jobject thiz) {
    return createJniLong(env, ticket_screen_periodic_data_2_lines_font_color);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_12_1zones(JNIEnv *env,
                                                                                    jobject thiz) {
    return createJniString(env, ticket_screen_periodic_data_2_zones);
}
JNIEXPORT jlong JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_12_1zones_1font_1color(
        JNIEnv *env, jobject thiz) {
    return createJniLong(env, ticket_screen_periodic_data_2_zones_font_color);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_12_1deletion_1date_1time(
        JNIEnv *env, jobject thiz) {
    return createJniString(env, ticket_screen_periodic_data_2_deletion_date_time);
}
JNIEXPORT jlong JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_12_1deletion_1date_1time_1font_1color(
        JNIEnv *env, jobject thiz) {
    return createJniLong(env, ticket_screen_periodic_data_2_deletion_date_time_font_color);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_12_1courseid(
        JNIEnv *env, jobject thiz) {
    return createJniString(env, ticket_screen_periodic_data_2_courseid);
}
JNIEXPORT jlong JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_12_1courseid_1font_1color(
        JNIEnv *env, jobject thiz) {
    return createJniLong(env, ticket_screen_periodic_data_2_courseid_font_color);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_12_1deletion_1type(
        JNIEnv *env, jobject thiz) {
    return createJniString(env, ticket_screen_periodic_data_2_deletion_type);
}
JNIEXPORT jlong JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_12_1deletion_1type_1font_1color(
        JNIEnv *env, jobject thiz) {
    return createJniLong(env, ticket_screen_periodic_data_2_deletion_type_font_color);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_12_1stop(JNIEnv *env,
                                                                                   jobject thiz) {
    return createJniString(env, ticket_screen_periodic_data_2_stop);
}
JNIEXPORT jlong JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_12_1stop_1font_1color(
        JNIEnv *env, jobject thiz) {
    return createJniLong(env, ticket_screen_periodic_data_2_stop_font_color);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_12_1zone(JNIEnv *env,
                                                                                   jobject thiz) {
    return createJniString(env, ticket_screen_periodic_data_2_zone);
}
JNIEXPORT jlong JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_12_1zone_1font_1color(
        JNIEnv *env, jobject thiz) {
    return createJniLong(env, ticket_screen_periodic_data_2_zone_font_color);
}
JNIEXPORT jstring JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_12_1line(JNIEnv *env,
                                                                                   jobject thiz) {
    return createJniString(env, ticket_screen_periodic_data_2_line);
}
JNIEXPORT jlong JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1periodic_1data_12_1line_1font_1color(
        JNIEnv *env, jobject thiz) {
    return createJniLong(env, ticket_screen_periodic_data_2_line_font_color);
}


JNIEXPORT jboolean JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_inspector_1screen_1is_1msg(JNIEnv *env, jobject thiz) {
    return screen_inspector_screen_is_msg;
}

JNIEXPORT jboolean JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1screen_1ticket_1state_1is_1msg(JNIEnv *env,
                                                                                  jobject thiz) {
    return ticket_screen_ticket_state_is_msg;
}
JNIEXPORT jboolean JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1state_1ep_1display_1expiration(
        JNIEnv *env, jobject thiz) {
    return ticket_state_ep_display_expiration;
}

JNIEXPORT jint JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ES_1RGIL_1SCREEN_1TICKET_1IS(JNIEnv *env, jobject thiz) {
    return GLOBAL_ES_RGIL_SCREEN_TICKET_IS;
}

JNIEXPORT jint JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ticket_1state_1ep_1icon(JNIEnv *env,
                                                                   jobject thiz) {
    return ticket_state_ep_icon;
}
JNIEXPORT jint JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ES_1RGIL_1SOUND_1CARD(JNIEnv *env, jobject thiz) {
    return GLOBAL_ES_RGIL_SOUND_CARD;
}
JNIEXPORT jint JNICALL
Java_com_rg_sprawdzarka_libraryjni_Library_ES_1RGIL_1SCREEN_1TYPE_1SCREEN(JNIEnv *env,
                                                                          jobject thiz) {
    return GLOBAL_ES_RGIL_SCREEN_TYPE_SCREEN;
}
}





//
//
//    fun main() {
//        val flags = mapOf(
//                EsRgilScreenTicketWhatIs.ES_RGIL_SCREEN_TICKET_IS_EP to true,
//                EsRgilScreenTicketWhatIs.ES_RGIL_SCREEN_TICKET_IS_PERIODIC1 to true,
//                EsRgilScreenTicketWhatIs.ES_RGIL_SCREEN_TICKET_IS_PERIODIC2 to false,
//                EsRgilScreenTicketWhatIs.ES_RGIL_SCREEN_TICKET_IS_PERSON to false
//        )
//
//        val result = calculateOrValue(flags)
//        println("Resulting value: $result")  // Powinno wydrukować: 3
//    }
//
//    typedef enum//flags - show what's on the ticket
//    {
//        ES_RGIL_SCREEN_TICKET_IS_EP_TEST = 0x01,
//        ES_RGIL_SCREEN_TICKET_IS_PERIODIC1_TEST = 0x02,
//        ES_RGIL_SCREEN_TICKET_IS_PERIODIC2_TEST = 0x04,
//        ES_RGIL_SCREEN_TICKET_IS_PERSON_TEST = 0x08,
//    } es_rgil_screen_ticket_what_isTEST;
//
//    if((screens.screen.ticket_screen.what_is & ES_RGIL_SCREEN_TICKET_IS_PERIODIC1) == ES_RGIL_SCREEN_TICKET_IS_PERIODIC1)
//        rgil_mplus_printf_periodic(screens, &screens.screen.ticket_screen.periodic_data_1, 1);
//
//    if((screens.screen.ticket_screen.what_is & ES_RGIL_SCREEN_TICKET_IS_PERIODIC2) == ES_RGIL_SCREEN_TICKET_IS_PERIODIC2)
//        rgil_mplus_printf_periodic(screens, &screens.screen.ticket_screen.periodic_data_2, 2);



//    enum
//    es_rgil_screen_ticket_what_is
//    {
//        ES_RGIL_SCREEN_TICKET_IS_EPT = 0x01,
//        ES_RGIL_SCREEN_TICKET_IS_PERIODIC1T = 0x02,
//        ES_RGIL_SCREEN_TICKET_IS_PERIODIC2T = 0x04,
//        ES_RGIL_SCREEN_TICKET_IS_PERSONT = 0x08,
//    } ;
//
//    if ((screens.screen.ticket_screen.what_is & ES_RGIL_SCREEN_TICKET_IS_EP) == ES_RGIL_SCREEN_TICKET_IS_EP) {
//        std::map<EsRgilScreenTicketWhatIs, bool> flags = {
//                {ES_RGIL_SCREEN_TICKET_IS_EP, true},};
//    } else {
//        std::map<EsRgilScreenTicketWhatIs, bool> flags = {
//                {ES_RGIL_SCREEN_TICKET_IS_EP, false},};
//    }
//    if ((screens.screen.ticket_screen.what_is & ES_RGIL_SCREEN_TICKET_IS_PERIODIC1) == ES_RGIL_SCREEN_TICKET_IS_PERIODIC1) {
//        std::map<EsRgilScreenTicketWhatIs, bool> flags = {
//                {GLOBAL_ES_RGIL_SCREEN_TICKET_IS_PERIODIC1, true},};
//    } else {
//        std::map<EsRgilScreenTicketWhatIs, bool> flags = {
//                {GLOBAL_ES_RGIL_SCREEN_TICKET_IS_PERIODIC1, false},};
//    }
//    if ((screens.screen.ticket_screen.what_is & ES_RGIL_SCREEN_TICKET_IS_PERIODIC2) == ES_RGIL_SCREEN_TICKET_IS_PERIODIC2) {
//        std::map<EsRgilScreenTicketWhatIs, bool> flags = {
//                {GLOBAL_ES_RGIL_SCREEN_TICKET_IS_PERIODIC2, true},};
//    } else {
//        std::map<EsRgilScreenTicketWhatIs, bool> flags = {
//                {GLOBAL_ES_RGIL_SCREEN_TICKET_IS_PERIODIC2, false},};
//    }
//    if ((screens.screen.ticket_screen.what_is & ES_RGIL_SCREEN_TICKET_IS_PERSON) == ES_RGIL_SCREEN_TICKET_IS_PERSON) {
//        std::map<EsRgilScreenTicketWhatIs, bool> flags = {
//                {GLOBAL_ES_RGIL_SCREEN_TICKET_IS_PERSON, true},};
//    } else {
//        std::map<EsRgilScreenTicketWhatIs, bool> flags = {
//                {GLOBAL_ES_RGIL_SCREEN_TICKET_IS_PERSON, false},};
//    }
//






