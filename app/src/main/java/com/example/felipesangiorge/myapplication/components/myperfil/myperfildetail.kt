package com.example.felipesangiorge.myapplication.components.myperfil

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.felipesangiorge.myapplication.R
import com.example.felipesangiorge.myapplication.`class`.MyPerfil
import com.example.felipesangiorge.myapplication.`class`.SportCategoryItem
import com.example.felipesangiorge.myapplication.database.DbWorkerThread
import com.example.felipesangiorge.myapplication.services.myperfil.MyPerfilServiceApi
import kotlinx.android.synthetic.main.item_dashboardbox.view.*
import kotlinx.android.synthetic.main.fragment_myperfildetail.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class myperfildetail : Fragment() {
    lateinit var adapter:MyPerfilCategoryAdapter
    private lateinit var mDbWorkerThread: DbWorkerThread

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_myperfildetail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()

        this.adapter = MyPerfilCategoryAdapter(emptyList())
        listViewSportCategory.adapter = adapter
        val result = MyPerfilServiceApi().loadMyPerfil()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { myPerfil ->
                    showMyPerfil(myPerfil)
                    adapter.replaceList(myPerfil.sportCategory)
                }, {
                    Log.e("returnlistTest","Error : $it")
                }, {
                    Log.e("returnlistTest","Complete")
                })

        }

    fun showMyPerfil(myPerfil: MyPerfil){
        textViewName.text = myPerfil.name
        textViewLastName.text = myPerfil.lastName
        textViewAddress.text = myPerfil.address
        textViewCity.text = myPerfil.city
        textViewCountry.text = myPerfil.country
        textViewPhone.text = myPerfil.cellPhone
    }
    class MyPerfilCategoryAdapter(var sportCategoryItem: List<SportCategoryItem>) : RecyclerView.Adapter<MyPerfilCategoryAdapter.ViewHolder>(){
        fun replaceList(sportCategoryItem: List<SportCategoryItem>){
            this.sportCategoryItem = sportCategoryItem.toMutableList()
            notifyDataSetChanged()
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_dashboardbox,parent,false))
        }

        override fun getItemCount(): Int {
            return sportCategoryItem.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(sportCategoryItem[position])
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            fun bind(sportCategoryItem: SportCategoryItem){
                itemView.textViewTitle.text=sportCategoryItem.sport
                itemView.textViewDescription.text=sportCategoryItem.category
                itemView.textViewCategory.text="${sportCategoryItem.priority}"
                itemView.textViewSport.text = "Priority:"
                itemView.textViewDate.text = ""
                itemView.textViewLocale.text = ""
            }
        }
    }

    override fun onDestroyView() {
        mDbWorkerThread.quit()
        super.onDestroyView()
    }
}
