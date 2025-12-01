package com.rg.krg13_dev.autocomputer.parser

data class CourseParameter(
    var L_lineNumber: String? = null,                 // L - Numer linii
    var I_lineTypeId: String? = null,                 // I - Id typu linii
    var X_stopNumber: String? = null,                 // X - Numer przystanku
    var K_courseNumber: String? = null,               // K - Numer kursu
    var k_courseNumberPoznanPeka: String? = null,     // k - Numer kursu – Poznań PEKA
    var S_postNumberS: String? = null,                // S - Numer słupka
    var S_numberOfStopsInCourse: String? = null,      // S - Ilość przystanków w kursie ( Piotrków Tryb, Kołobrzeg (Litwa – Kowno), Rybnik)
    var A_vehicleNumber: String? = null,              // A - Numer pojazdu
    var R_courseType: String? = null,                 // R - rodzaj kursu
    var R_courseTypeKownoKautra: String? = null,      // R - Rodzaj kursu – Kowno/Kautra
    var R_unknownPurpose: String? = null,             // R - Nieznane przeznaczenie
    var Y_stopSequenceNumber: String? = null,         // Y - Numer kolejny przystanku w kursie
    var y_stopAttributes: String? = null,             // y - Atrybuty przystanku
    var y_stopIdsForGadaczOlsztyn: String? = null,    // y - ID przystanków wg ID dla Gadacza dla miasta Olsztyn
    var D_defaultFareNumber: String? = null,          // D - Numer taryfy domyślnej
    var D_transportKWSR: String? = null,              // D - transport - Jeden znak – KWSR Poznań
    var D_transportPekaPoznan: String? = null,        // D - transport - PEKA Poznań
    var D_transportKownoKautra: String? = null,       // D - transport - Kowno/Kautra
    var E_brigadeNumber: String? = null,              // E - Numer brygady
    var G_courseStatus: String? = null,               // G - Stan realizacji kursu
    var g_municipalityId: String? = null,             // g - Identyfikator gminy
    var P_additionalScheduleDigits: String? = null,   // P - Cyfry dodatkowe z rozkładu jazdy
    var Z_zoneNumber: String? = null,                 // Z - Numer strefy
    var W_routeVariant: String? = null,               // W - Wariant trasy
    var W_routeVariantFlags: String? = null,          // w - Flagi wariantu tras
    var Q_lineCourseSequence: String? = null,         // Q - Numer kolejny kursu na linii
    var C_dayTypeFromSchedule: String? = null,        // C - Typ dnia pobrany z rozkładu jazdy
    var c_dayFlagsFromTKalendarz: String? = null,     // c - Flagi dnia ze struktury TKalendarz z rozkładu jazdy
    var B_lineIdentifier: String? = null,             // B - Identyfikator linii
    var V_vlakStopNumber: String? = null,             // V - Numer przystanku dla VLAK
    var V_carrierId: String? = null,                  // V - Identyfikator przewoźnika
    var N_courseZoneMarker: String? = null,           // N - znacznik typu kursu (ilość stref w realizowanym kursie)
    var n_directionName: String? = null,              // n - Nazwa kierunku
    var I_terminusNumber: String? = null,             // I - Numer końcówki
    var I_postNumberI: String? = null,                // I - Numer słupka
    var J_boardingZoneNumber: String? = null,         // J - Numer strefy dla wsiadającego
    var Z_alightingZoneNumber: String? = null,        // Z - Numer strefy dla wysiadającego
    var N_maxRemainingZoneNumber: String? = null,     // N - Maksymalny numer strefy od położenia aktualnego do końca realizowanego kursu
    var F_passedZonesInfo: String? = null,            // F - Info o strefach przejeżdżanych/Info o zmianie stref na odcinkach
    var H_loopSectionsInfo: String? = null,           // H - Info o tzw. kieszeniach przejazdowych
    var T_boardingStopName: String? = null,           // T - Nazwa przystanku dla wsiadających
    var t_alightingStopName: String? = null,          // t - Nazwa przystanku dla wysiadających
    var M_pointInfoDisplaySystem: String? = null,     // M - System wyświetlania informacji o punktach
    var O_operatorNumber: String? = null,             // O - Numer operatora
    var e_selectedTeamName: String? = null,           // e - nazwa aktualnie wybranej brygady
    var u_courseStartTime: String? = null,            // u - Czas rozpoczęcia realizowanego kursu
    var a_timetableDate: String? = null,              // a - Data RJ


)