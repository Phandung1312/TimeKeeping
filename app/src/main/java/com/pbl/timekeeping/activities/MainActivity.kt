package com.pbl.timekeeping.activities

import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.pbl.timekeeping.R
import com.pbl.timekeeping.base.activities.BaseActivity
import com.pbl.timekeeping.base.dialogs.LoadingDialog
import com.pbl.timekeeping.data.models.Account
import com.pbl.timekeeping.data.models.Employee
import com.pbl.timekeeping.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var navController: NavController
    private lateinit var loadingDialog: LoadingDialog
    var employee: Employee = Employee()
    lateinit var  binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setNavController()
        initViews()
    }
    private fun setNavController(){
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.department ->{
                    navController.navigate(R.id.departmentFragment)
                    true
                }
                R.id.workHistory ->{
                    navController.navigate(R.id.historyAttendanceFragment)
                    true
                }
                R.id.profile ->{
                    navController.navigate(R.id.accountFragment)
                    true
                }
                else -> false
            }

        }
    }
    private fun initViews(){
        loadingDialog = LoadingDialog(this)
    }

    override fun showSmallLoading(isShow: Boolean) {

    }

    override fun showScreenLoading(isShow: Boolean) {
        if(isShow){
            loadingDialog.show()
        }
        else{
            loadingDialog.dismiss()
        }
    }

}