package com.example.felipesangiorge.myapplication

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.felipesangiorge.myapplication.`class`.SportItem
import com.example.felipesangiorge.myapplication.services.dashboard.DashboardServiceApi
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.item_dashboardbox.view.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers




// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [DashboardFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [DashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class DashboardFragment : Fragment() {
    val TAG = "FRAGMENT_DASHBOARD"
    lateinit var adapter:SportListAdapter

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
    }

    override fun onAttach(context: Context) {
        Log.d(TAG,"OnAtach")
        super.onAttach(context)
    }

    override fun onDetach() {
        Log.d(TAG,"OnDetach")
        super.onDetach()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        swipeRefreshDashboard.setColorSchemeResources(R.color.colorPrimary)
        swipeRefreshDashboard!!.setOnRefreshListener {
            refreshSportListData()
        }
        this.adapter = SportListAdapter(emptyList())
        recycleViewSportList.adapter = adapter
        refreshSportListData()
    }
    fun refreshSportListData(){
        DashboardServiceApi() //Loading Sport List on Dashboard
            .loadSportList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { sportList ->
                    adapter.replaceList(sportList)
                }, {
                    Toast.makeText(context,resources.getText(R.string.load_error),Toast.LENGTH_LONG).show()
                }, {
                    Toast.makeText(context,resources.getText(R.string.refresh_complete),Toast.LENGTH_SHORT).show()
                    swipeRefreshDashboard.isRefreshing = false
                })
    }
    class SportListAdapter(var sportList : List<SportItem>) : RecyclerView.Adapter<SportListAdapter.ViewHolder>(){
        fun replaceList(sportList: List<SportItem>){
            this.sportList = sportList.toMutableList()
            notifyDataSetChanged()
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_dashboardbox,parent,false))
        }

        override fun getItemCount(): Int {
            return sportList.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(sportList[position])
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            fun bind(sportList: SportItem){
                itemView.textViewTitle.text=sportList.title
                itemView.textViewDescription.text=sportList.description
                itemView.textViewSport.text=sportList.sport
                itemView.textViewCategory.text=sportList.category
                itemView.textViewLocale.text=sportList.locale
                itemView.textViewDate.text=sportList.date
                itemView.setOnClickListener{
                    Log.e("Click","Clicked on ${sportList.title}")

                }
            }
        }
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */


}
