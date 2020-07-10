package com.justmsme.udyam

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.justmsme.udyam.data.Faq
import com.justmsme.udyam.data.ServiceItem
import org.json.JSONArray


class MainActivity : AppCompatActivity() {

    companion object {
        const val FAQS = "faqs"
        const val SLIDER_IMAGES = "header_images"
        const val SERVICES = "services"

        fun sliderImages(): ArrayList<String> {
            val array = ArrayList<String>()
            val jsonArray = JSONArray(Firebase.remoteConfig.getString(SLIDER_IMAGES))
            for (i in 0 until jsonArray.length()) {
                val url = jsonArray.getString(i)
                array.add(url)
            }
            return array
        }

        fun faqs(): ArrayList<Faq> {
            val array = ArrayList<Faq>()
            val jsonArray = JSONArray(Firebase.remoteConfig.getString(FAQS))
            for (i in 0 until jsonArray.length()) {
                val faq = jsonArray.getJSONObject(i)
                array.add(
                    Faq(
                        question = faq.getString("question"),
                        answer = faq.getString("answer")
                    )
                )
            }
            return array
        }

        fun services(): ArrayList<ServiceItem> {
            val array = ArrayList<ServiceItem>()
            val jsonArray = JSONArray(Firebase.remoteConfig.getString(SERVICES))
            for (i in 0 until jsonArray.length()) {
                val faq = jsonArray.getJSONObject(i)
                array.add(
                    ServiceItem(
                        title = faq.getString("title"),
                        iconUrl = faq.getString("iconUrl"),
                        formUrl = faq.getString("formUrl")
                    )
                )
            }
            return array
        }
    }

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_Light)
        super.onCreate(savedInstanceState)
        setupFirebaseConfigs()
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_about,
                R.id.nav_contact,
                R.id.nav_faqs,
                R.id.nav_process
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun setupFirebaseConfigs() {
        val remoteConfig = Firebase.remoteConfig
//        val configSettings = remoteConfigSettings {
//            minimumFetchIntervalInSeconds = 300
//        }
//        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(
            mapOf(
                FAQS to "[{\"question\":\"What are Micro, Small and Medium Enterprise?\",\"answer\":\"<div>MSME are classified into two categories:<div> <br> <ul> <li> <span>Manufacturing enterprise; and</span> </li> <li> <span>Service enterprise. </span> </li> </ul> <br> <div>They are defined in term of investment in Plant and Machinery/ Equipment and turnover as below:- <div> <br> <div ><strong>Micro Enterprise -</strong> Investment in Plant and Machinery or Equipment: </div> <div >Not more than Rs.1 crore and Annual Turnover ; not more than Rs. 5 crore</div> <div ><strong>Small Enterprise -</strong> Investment in Plant and Machinery or Equipment:</div> <div >Not more than Rs.10 crore and Annual Turnover ; not more than Rs. 50 crore</div> <div><strong>Medium Enterprise -</strong> - Investment in Plant and Machinery or Equipment: </div> <div> Not more than Rs.50 crore and Annual Turnover ; not more than Rs. 250 crore </div>\"},{\"question\":\"Do i need to be physically present during the registration?\",\"answer\":\"Not really. It is an online process and your physical presence is not required.\"},{\"question\":\"Will I be charged a fee for the registration?\",\"answer\":\"Yes. To get an error-free certificate from us, you’ll be charged a little amount as the consultancy charge.\"},{\"question\":\"How many days it might take to get your Udyam registration number?\",\"answer\":\"It will take just 24 hours if you have provided the information correctly.\"},{\"question\":\"Can I apply for it online?\",\"answer\":\"Yes, of course. The online registration from us is simpler, easier, more hassle-free, and completely error-free. Plus, you’ll also get advice from top-rated experts.\"},{\"question\":\"Is it government authorized?\",\"answer\":\"Yes. You can avail all benefits of MSME/Udyog by this certificate.\"},{\"question\":\"Is it compulsory to register for Udyam if you have a company?\",\"answer\":\"If your company falls under the MSME category and you have registered for the MSME/ Udyog Aadhar, Udyam registration is compulsory for you. Basically, it is compulsory for only old MSME/Udyog Aadhar registered organizations. The government has given the due date up to 31st March 2021 for all existing MSME companies to register for Udyam.\"},{\"question\":\"Can a new company apply for it?\",\"answer\":\"Yes, it can. Any new or only company can apply for it.\"},{\"question\":\"Is Aadhar mandatory?\",\"answer\":\"Yes, if your company is private limited/partnership, Aadhar card of any directors or partners can be attached. However, if the company is proprietor, the Aadhar of the proprietor is required.\"}]",
                SLIDER_IMAGES to "[\"https://firebasestorage.googleapis.com/v0/b/udyam-fb9db.appspot.com/o/header_two.png?alt=media&token=91989193-a111-48f7-a5b8-ab82285d7118\",\"https://firebasestorage.googleapis.com/v0/b/udyam-fb9db.appspot.com/o/header_one.png?alt=media&token=c726786d-bd53-499f-966d-c5ca7d597fb0\"]",
                SERVICES to "[{\"title\":\"MSME / Udyog / Udyam Registration\",\"iconUrl\":\"https://firebasestorage.googleapis.com/v0/b/udyam-fb9db.appspot.com/o/register.png?alt=media&token=4bb06382-199f-459f-8f7f-faf0de8cec5a\",\"formUrl\":\"https://forms.justmsme.com/registerinmsme/form/UdyamMSMEUdyogRegistrationFORM/formperma/Fje6P8yhj0KCB0XfLdOGTa3yh0R4dMtT0BNecEzkBsQ\"},{\"title\":\"Update Your MSME Certificate\",\"iconUrl\":\"https://firebasestorage.googleapis.com/v0/b/udyam-fb9db.appspot.com/o/update.png?alt=media&token=5999c2aa-6516-425f-bbe9-0e47c3af3c82\",\"formUrl\":\"https://forms.justmsme.com/registerinmsme/form/UpdateMSMEUdyogRegistration/formperma/6wISEYHjp4LteRQ0KgGkq-V7WcxbCw_r0HnoqhKTmVA\"},{\"title\":\"Book Consultation\",\"iconUrl\":\"https://firebasestorage.googleapis.com/v0/b/udyam-fb9db.appspot.com/o/question.png?alt=media&token=b6f5b11f-2e71-4031-9c05-70ba464bc05d\",\"formUrl\":\"https://forms.justmsme.com/registerinmsme/form/MSMEAdviceConsultationByExperts/formperma/aVUESfXmsZsrZUMTiAFxRd0CRAfDmYNQ3E6AIznFtQA\"},{\"title\":\"Trademark Registration\",\"iconUrl\":\"https://firebasestorage.googleapis.com/v0/b/udyam-fb9db.appspot.com/o/patent.png?alt=media&token=f82b3e32-cd0a-450c-b173-6b36f437d826\",\"formUrl\":\"https://forms.justmsme.com/registerinmsme/form/TRADEMARK/formperma/jrFS23epR15NnuIgNa-moOcJiLwep67lqkoo03IMG0g\"}]"
            )
        )
        remoteConfig.fetchAndActivate()
    }

    override fun onBackPressed() {
        return when (navController.currentDestination?.id) {
            R.id.nav_home_second -> {
                this.showDialog("Are you sure you want to go back?", "Go Back", "Cancel", {
                    super.onBackPressed()
                }, {})
            }
            R.id.nav_home -> {
                this.showDialog("Are you sure you want to exit the app?", "Exit", "Cancel", {
                    super.onBackPressed()
                }, {})
            }
            else -> super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return if (navController.currentDestination?.id == R.id.nav_home_second) {
            this.showDialog("Are you sure you want to go back?", "Go Back", "Cancel", {
                navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
            }, {})
            true
        } else navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}