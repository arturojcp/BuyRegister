package com.example.buyregister.APIHelper

import com.google.gson.annotations.SerializedName


data class USD (

    @SerializedName("transferencia"    ) var transferencia   : Double? = null,
    @SerializedName("transfer_cucuta"  ) var transferCucuta  : Double? = null,
    @SerializedName("efectivo"         ) var efectivo        : Double? = null,
    @SerializedName("efectivo_real"    ) var efectivoReal    : Double? = null,
    @SerializedName("efectivo_cucuta"  ) var efectivoCucuta  : Double? = null,
    @SerializedName("promedio"         ) var promedio        : Double? = null,
    @SerializedName("promedio_real"    ) var promedioReal    : Double? = null,
    @SerializedName("cencoex"          ) var cencoex         : Double? = null,
    @SerializedName("sicad1"           ) var sicad1          : Double? = null,
    @SerializedName("sicad2"           ) var sicad2          : Double? = null,
    @SerializedName("bitcoin_ref"      ) var bitcoinRef      : Double? = null,
    @SerializedName("localbitcoin_ref" ) var localbitcoinRef : Double? = null,
    @SerializedName("dolartoday"       ) var dolartoday      : Double? = null

    )

data class respuestaDolar (

    @SerializedName("_antibloqueo" ) var Antibloqueo : Antibloqueo? = Antibloqueo(),
    @SerializedName("_labels"      ) var Labels      : Labels?      = Labels(),
    @SerializedName("_timestamp"   ) var Timestamp   : Timestamp?   = Timestamp(),
    @SerializedName("USD"          ) var USD         : USD?         = USD(),
    @SerializedName("EUR"          ) var EUR         : EUR?         = EUR(),
    @SerializedName("COL"          ) var COL         : COL?         = COL(),
    @SerializedName("GOLD"         ) var GOLD        : GOLD?        = GOLD(),
    @SerializedName("USDVEF"       ) var USDVEF      : USDVEF?      = USDVEF(),
    @SerializedName("USDCOL"       ) var USDCOL      : USDCOL?      = USDCOL(),
    @SerializedName("EURUSD"       ) var EURUSD      : EURUSD?      = EURUSD(),
    @SerializedName("BCV"          ) var BCV         : BCV?         = BCV(),
    @SerializedName("MISC"         ) var MISC        : MISC?        = MISC()

)

data class Antibloqueo (

    @SerializedName("mobile"                    ) var mobile                   : String? = null,
    @SerializedName("video"                     ) var video                    : String? = null,
    @SerializedName("corto_alternativo"         ) var cortoAlternativo         : String? = null,
    @SerializedName("enable_iads"               ) var enableIads               : String? = null,
    @SerializedName("enable_admobbanners"       ) var enableAdmobbanners       : String? = null,
    @SerializedName("enable_admobinterstitials" ) var enableAdmobinterstitials : String? = null,
    @SerializedName("alternativo"               ) var alternativo              : String? = null,
    @SerializedName("alternativo2"              ) var alternativo2             : String? = null,
    @SerializedName("notifications"             ) var notifications            : String? = null,
    @SerializedName("resource_id"               ) var resourceId               : String? = null

)

data class Labels (

    @SerializedName("a"  ) var a  : String? = null,
    @SerializedName("a1" ) var a1 : String? = null,
    @SerializedName("b"  ) var b  : String? = null,
    @SerializedName("c"  ) var c  : String? = null,
    @SerializedName("d"  ) var d  : String? = null,
    @SerializedName("e"  ) var e  : String? = null

)


data class Timestamp (

    @SerializedName("epoch"        ) var epoch       : String? = null,
    @SerializedName("fecha"        ) var fecha       : String? = null,
    @SerializedName("fecha_corta"  ) var fechaCorta  : String? = null,
    @SerializedName("fecha_corta2" ) var fechaCorta2 : String? = null,
    @SerializedName("fecha_nice"   ) var fechaNice   : String? = null,
    @SerializedName("dia"          ) var dia         : String? = null,
    @SerializedName("dia_corta"    ) var diaCorta    : String? = null

)

data class EUR (

    @SerializedName("transferencia"   ) var transferencia  : Double? = null,
    @SerializedName("transfer_cucuta" ) var transferCucuta : Double? = null,
    @SerializedName("efectivo"        ) var efectivo       : Double? = null,
    @SerializedName("efectivo_real"   ) var efectivoReal   : Double? = null,
    @SerializedName("efectivo_cucuta" ) var efectivoCucuta : Double? = null,
    @SerializedName("promedio"        ) var promedio       : Double? = null,
    @SerializedName("promedio_real"   ) var promedioReal   : Double? = null,
    @SerializedName("cencoex"         ) var cencoex        : Double? = null,
    @SerializedName("sicad1"          ) var sicad1         : Double? = null,
    @SerializedName("sicad2"          ) var sicad2         : Double? = null,
    @SerializedName("dolartoday"      ) var dolartoday     : Double? = null

)

data class COL (

    @SerializedName("efectivo" ) var efectivo : Int?    = null,
    @SerializedName("transfer" ) var transfer : Int?    = null,
    @SerializedName("compra"   ) var compra   : Int?    = null,
    @SerializedName("venta"    ) var venta    : Double? = null

)

data class GOLD (

    @SerializedName("rate" ) var rate : Int? = null

)


data class USDVEF (

    @SerializedName("rate" ) var rate : Int? = null

)

data class USDCOL (

    @SerializedName("setfxsell"     ) var setfxsell     : Int?    = null,
    @SerializedName("setfxbuy"      ) var setfxbuy      : Int?    = null,
    @SerializedName("rate"          ) var rate          : Int?    = null,
    @SerializedName("ratecash"      ) var ratecash      : Int?    = null,
    @SerializedName("ratetrm"       ) var ratetrm       : Int?    = null,
    @SerializedName("trmfactor"     ) var trmfactor     : Double? = null,
    @SerializedName("trmfactorcash" ) var trmfactorcash : Double? = null

)


data class EURUSD (

    @SerializedName("rate" ) var rate : Double? = null

)


data class BCV (

    @SerializedName("fecha"      ) var fecha     : String? = null,
    @SerializedName("fecha_nice" ) var fechaNice : String? = null,
    @SerializedName("liquidez"   ) var liquidez  : String? = null,
    @SerializedName("reservas"   ) var reservas  : String? = null

)


data class MISC (

    @SerializedName("petroleo" ) var petroleo : String? = null,
    @SerializedName("reservas" ) var reservas : String? = null

)