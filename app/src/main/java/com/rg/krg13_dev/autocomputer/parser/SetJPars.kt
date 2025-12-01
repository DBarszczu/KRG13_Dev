package com.rg.krg13_dev.autocomputer.parser

import android.util.Log

class SetJPars {

    fun parse(raw: String): CourseParameter {
        val cleanRaw = raw.drop(1)
        val params = CourseParameter()
        val pairs = cleanRaw.split(":")

        var i = 0
        while (i < pairs.size) {
            val key = pairs[i]
            val value = pairs.getOrNull(i + 1) ?: break


            when (key) {
                "L" -> {
                    params.L_lineNumber = value
                    Log.d("SetJPars", "L (Numer linii): $value")
                }
                "I" -> {
                    params.I_lineTypeId = value
                    Log.d("SetJPars", "I (Id typu linii): $value")
                }
                "X" -> {
                    params.X_stopNumber = value
                    Log.d("SetJPars", "X (Numer przystanku): $value")
                }
                "K" -> {
                    params.K_courseNumber = value
                    Log.d("SetJPars", "K (Numer kursu): $value")
                }
                "k" -> {
                    params.k_courseNumberPoznanPeka = value
                    Log.d("SetJPars", "K (Numer kursu – Poznań PEKA): $value")
                }
//                "S" -> {
//                    params.S_postNumberS = value
//                    Log.d("SetJPars", "S (Numer słupka): $value")
//                }
                "S" -> {
                    params.S_numberOfStopsInCourse = value
                    Log.d("SetJPars", "S (Ilość przystanków w kursie): $value")
                }

                "A" -> {
                    params.A_vehicleNumber = value
                    Log.d("SetJPars", "A (Numer pojazdu): $value")
                }

                "R" -> {
                    params.R_courseType = value
                    Log.d("SetJPars", "R (Rodzaj kursu): $value")
                }
//                "R" -> {
//                    params.R_courseTypeKownoKautra = value
//                    Log.d("SetJPars", "R (Rodzaj kursu – Kowno/Kautra): $value")
//                }
//                "R" -> {
//                    params.R_unknownPurpose = value
//                    Log.d("SetJPars", "R (Nieznane przeznaczenie): $value")
//                }
                "Y" -> {
                    params.Y_stopSequenceNumber = value
                    Log.d("SetJPars", "Y (Numer kolejny przystanku w kursie): $value")
                }
                "y" -> {
                    params.y_stopAttributes = value
                    Log.d("SetJPars", "Y (Atrybuty przystanku): $value")
                }
//                "y" -> {
//                    params.y_stopIdsForGadaczOlsztyn = value
//                    Log.d("SetJPars", "Y (ID przystanków wg ID dla Gadacza dla miasta Olsztyn): $value")
//                }
//                "D" -> {
//                    params.D_defaultFareNumber = value
//                    Log.d("SetJPars", "Y (Numer taryfy domyślnej): $value")
//                }
                "D" -> {
                    params.D_defaultFareNumber = value
                    Log.d("SetJPars", "D (transport - Jeden znak – KWSR Poznań): $value")
                }
//                "D" -> {
//                    params.D_defaultFareNumber = value
//                    Log.d("SetJPars", "D (transport - PEKA Poznań): $value")
//                }
//                "D" -> {
//                    params.D_defaultFareNumber = value
//                    Log.d("SetJPars", "D (transport - Kowno/Kautra): $value")
//                }
                "E" -> {
                    params.E_brigadeNumber = value
                    Log.d("SetJPars", "Y (Numer brygady): $value")
                }
                "G" -> {
                    params.G_courseStatus = value
                    Log.d("SetJPars", "G (Stan realizacji kursu): $value")
                }
                "g" -> {
                    params.g_municipalityId = value
                    Log.d("SetJPars", "G (Identyfikator gminy): $value")
                }
                "P" -> {
                    params.P_additionalScheduleDigits = value
                    Log.d("SetJPars", "P (Cyfry dodatkowe z rozkładu jazdy): $value")
                }
                "Z" -> {
                    params.Z_zoneNumber = value
                    Log.d("SetJPars", "Z (Numer strefy): $value")
                }

                "W" -> {
                    params.W_routeVariant = value
                    Log.d("SetJPars", "O (Wariant trasy): $value")
                }
//                "W" -> {
//                    params.W_routeVariantFlags = value
//                    Log.d("SetJPars", "O (Flagi wariantu tras): $value")
//                }
                "Q" -> {
                    params.Q_lineCourseSequence = value
                    Log.d("SetJPars", "Q (Numer kolejny kursu na linii): $value")
                }
                "C" -> {
                    params.C_dayTypeFromSchedule = value
                    Log.d("SetJPars", "C (Typ dnia pobrany z rozkładu jazdy): $value")
                }
                "c" -> {
                    params.c_dayFlagsFromTKalendarz = value
                    Log.d("SetJPars", "c (Flagi dnia ze struktury TKalendarz z rozkładu jazdy): $value")
                }
                "B" -> {
                    params.B_lineIdentifier = value
                    Log.d("SetJPars", "B (Identyfikator linii): $value")
                }
//                "V" -> {
//                    params.V_vlakStopNumber = value
//                    Log.d("SetJPars", "V (Numer przystanku dla VLAK): $value")
//                }
                "V" -> {
                    params.V_carrierId = value
                    Log.d("SetJPars", "V (Identyfikator przewoźnika): $value")
                }
                "N" -> {
                    params.N_courseZoneMarker = value
                    Log.d(
                        "SetJPars",
                        "N (znacznik typu kursu (ilość stref w realizowanym kursie)): $value"
                    )
                }
                "n" -> {
                    params.n_directionName = value
                    Log.d(
                        "SetJPars",
                        "N (Nazwa kierunku)): $value"
                    )
                }
//                "I" -> {
//                    params.I_terminusNumber = value
//                    Log.d("SetJPars", "I (Numer końcówki): $value")
//                }
//                "I" -> {
//                    params.I_postNumberI = value
//                    Log.d("SetJPars", "I (Numer słupka): $value")
//                }
                "J" -> {
                    params.J_boardingZoneNumber = value
                    Log.d("SetJPars", "J (Numer strefy dla wsiadającego): $value")
                }
//                "Z" -> {
//                    params.Z_alightingZoneNumber = value
//                    Log.d("SetJPars", "I (Numer strefy dla wysiadającego): $value")
//                }
//                "N" -> {
//                    params.N_maxRemainingZoneNumber = value
//                    Log.d("SetJPars", "I (Maksymalny numer strefy do końca realizowanego kursu): $value")
//                }
                "F" -> {
                    params.F_passedZonesInfo = value
                    Log.d("SetJPars", "F (Info o strefach przejeżdżanych): $value")
                }
                "H" -> {
                    params.H_loopSectionsInfo = value
                    Log.d("SetJPars", "H (Info o tzw. kieszeniach przejazdowych): $value")
                }
                "T" -> {
                    params.T_boardingStopName = value
                    Log.d("SetJPars", "T (Nazwa przystanku dla wsiadających): $value")
                }
                "t" -> {
                    params.t_alightingStopName = value
                    Log.d("SetJPars", "t (Nazwa przystanku dla wysiadających): $value")
                }
                "M" -> {
                    params.M_pointInfoDisplaySystem = value
                    Log.d("SetJPars", "M (System wyświetlania informacji o punktach): $value")
                }
                "O" -> {
                    params.M_pointInfoDisplaySystem = value
                    Log.d("SetJPars", "O ( Numer operatora): $value")
                }
                "e" -> {
                    params.e_selectedTeamName = value
                    Log.d("SetJPars", "e (nazwa aktualnie wybranej brygady): $value")
                }
                "u" -> {
                    params.u_courseStartTime = value
                    Log.d("SetJPars", "u (Czas rozpoczęcia realizowanego kursu): $value")
                }
                "a" -> {
                    params.a_timetableDate = value
                    Log.d("SetJPars", "u (Data RJ): $value")
                }

                else -> {
                    Log.d("SetJPars", "Nierozpoznany identyfikator: $key z wartością: $value")
                }
            }

            i += 2
        }

        return params
    }
}
