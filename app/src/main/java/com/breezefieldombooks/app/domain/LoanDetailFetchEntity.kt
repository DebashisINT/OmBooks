package com.breezefieldombooks.app.domain

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import com.breezefieldombooks.app.AppConstant

@Entity(tableName = AppConstant.Loan_Detail_Fetch)
data class LoanDetailFetchEntity(
    @PrimaryKey(autoGenerate = true) var sl_no: Int = 0,
    @ColumnInfo var shop_id:String = "",
    @ColumnInfo var bkt:String = "",
    @ColumnInfo var total_outstanding:String = "",
    @ColumnInfo var pos:String = "",
    @ColumnInfo var emi_amt:String = "",
    @ColumnInfo var all_charges:String = "",
    @ColumnInfo var total_Collectable:String = "",
    @ColumnInfo var risk_id:String = "",
    @ColumnInfo var risk_name:String = "",
    @ColumnInfo var workable:String = "",
    @ColumnInfo var disposition_code_id:String = "",
    @ColumnInfo var disposition_code_name:String = "",
    @ColumnInfo var ptp_Date:String = "",
    @ColumnInfo var ptp_amt:String = "",
    @ColumnInfo var collection_date:String = "",
    @ColumnInfo var collection_amount:String = "",
    @ColumnInfo var final_status_id:String = "",
    @ColumnInfo var final_status_name:String = ""
)


@Dao
interface LoanDetailFetchDao {
    @Insert
    fun insert(vararg obj: LoanDetailFetchEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun insertAll(kist: List<LoanDetailFetchEntity>)

    @Query("select * from loan_detail_fetch")
    fun getAll():List<LoanDetailFetchEntity>

}