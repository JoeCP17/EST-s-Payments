package com.example.estpayments.domain.remittance

data class Remittances(
    val remittances: List<Remittance>
) {
    private var remittancesList = mutableListOf<Remittance>()

    init {
        require(remittancesList.isNotEmpty()) { "송금 내역이 비어있습니다. 다시 확인해주세요." }
        remittancesList.addAll(remittances)
    }

}
