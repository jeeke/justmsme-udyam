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
        const val FORM_URLS = "form_urls"
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
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 300
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(
            mapOf(
                FORM_URLS to "[\"https://forms.justmsme.com/registerinmsme/form/GSTFILING/formperma/s_kaTURw9fSEdVqNMbQslJXt5ER8nLQk0T-0w2zUUpE?zf_rszfm=1\",\"https://forms.justmsme.com/registerinmsme/form/GSTFILING/formperma/s_kaTURw9fSEdVqNMbQslJXt5ER8nLQk0T-0w2zUUpE?zf_rszfm=1\",\"https://forms.justmsme.com/registerinmsme/form/GSTFILING/formperma/s_kaTURw9fSEdVqNMbQslJXt5ER8nLQk0T-0w2zUUpE?zf_rszfm=1\",\"https://forms.justmsme.com/registerinmsme/form/GSTFILING/formperma/s_kaTURw9fSEdVqNMbQslJXt5ER8nLQk0T-0w2zUUpE?zf_rszfm=1\"]",
                FAQS to "[{\"question\":\"What are Micro, Small and Medium Enterprise?\",\"answer\":\"<div>MSME are classified into two categories:<div> <br> <ul> <li> <span>Manufacturing enterprise; and</span> </li> <li> <span>Service enterprise. </span> </li> </ul> <br> <div>They are defined in term of investment in Plant and Machinery/ Equipment and turnover as below:- <div> <br> <div ><strong>Micro Enterprise -</strong> Investment in Plant and Machinery or Equipment: </div> <div >Not more than Rs.1 crore and Annual Turnover ; not more than Rs. 5 crore</div> <div ><strong>Small Enterprise -</strong> Investment in Plant and Machinery or Equipment:</div> <div >Not more than Rs.10 crore and Annual Turnover ; not more than Rs. 50 crore</div> <div><strong>Medium Enterprise -</strong> - Investment in Plant and Machinery or Equipment: </div> <div> Not more than Rs.50 crore and Annual Turnover ; not more than Rs. 250 crore </div>\"},{\"question\":\"Do i need to be physically present during the registration?\",\"answer\":\"Not really. It is an online process and your physical presence is not required.\"},{\"question\":\"Will I be charged a fee for the registration?\",\"answer\":\"Yes. To get an error-free certificate from us, you’ll be charged a little amount as the consultancy charge.\"},{\"question\":\"How many days it might take to get your Udyam registration number?\",\"answer\":\"It will take just 24 hours if you have provided the information correctly.\"},{\"question\":\"Can I apply for it online?\",\"answer\":\"Yes, of course. The online registration from us is simpler, easier, more hassle-free, and completely error-free. Plus, you’ll also get advice from top-rated experts.\"},{\"question\":\"Is it government authorized?\",\"answer\":\"Yes. You can avail all benefits of MSME/Udyog by this certificate.\"},{\"question\":\"Is it compulsory to register for Udyam if you have a company?\",\"answer\":\"If your company falls under the MSME category and you have registered for the MSME/ Udyog Aadhar, Udyam registration is compulsory for you. Basically, it is compulsory for only old MSME/Udyog Aadhar registered organizations. The government has given the due date up to 31st March 2021 for all existing MSME companies to register for Udyam.\"},{\"question\":\"Can a new company apply for it?\",\"answer\":\"Yes, it can. Any new or only company can apply for it.\"},{\"question\":\"Is Aadhar mandatory?\",\"answer\":\"Yes, if your company is private limited/partnership, Aadhar card of any directors or partners can be attached. However, if the company is proprietor, the Aadhar of the proprietor is required.\"}]",
                SLIDER_IMAGES to "[\"https://firebasestorage.googleapis.com/v0/b/udyam-fb9db.appspot.com/o/header_two.png?alt=media&token=5a4f3f55-f128-469a-9d26-df304eb1105d\",\"https://firebasestorage.googleapis.com/v0/b/udyam-fb9db.appspot.com/o/header_one.png?alt=media&token=c726786d-bd53-499f-966d-c5ca7d597fb0\"]",
                SERVICES to "[{\"title\":\"MSME / Udyog / Udyam Registration\",\"iconUrl\":\"https://firebasestorage.googleapis.com/v0/b/udyam-fb9db.appspot.com/o/register.svg?alt=media&token=6ca7a607-c880-4394-a071-6ba4427a9daa\",\"formUrl\":\"https://forms.justmsme.com/registerinmsme/form/GSTFILING/formperma/s_kaTURw9fSEdVqNMbQslJXt5ER8nLQk0T-0w2zUUpE?zf_rszfm=1\"},{\"title\":\"Update Your MSME Certificate\",\"iconUrl\":\"https://firebasestorage.googleapis.com/v0/b/udyam-fb9db.appspot.com/o/update.svg?alt=media&token=0e8dfaa9-bdcb-4c5b-a13c-d385db4d3934\",\"formUrl\":\"https://forms.justmsme.com/registerinmsme/form/GSTFILING/formperma/s_kaTURw9fSEdVqNMbQslJXt5ER8nLQk0T-0w2zUUpE?zf_rszfm=1\"},{\"title\":\"Book Consultation\",\"iconUrl\":\"https://firebasestorage.googleapis.com/v0/b/udyam-fb9db.appspot.com/o/question.svg?alt=media&token=6925b3be-0731-43df-a8db-5faff6fef208\",\"formUrl\":\"https://forms.justmsme.com/registerinmsme/form/GSTFILING/formperma/s_kaTURw9fSEdVqNMbQslJXt5ER8nLQk0T-0w2zUUpE?zf_rszfm=1\"},{\"title\":\"Trademark Registration\",\"iconUrl\":\"https://firebasestorage.googleapis.com/v0/b/udyam-fb9db.appspot.com/o/patent.svg?alt=media&token=0095d189-ba69-46d4-ad0c-846e1e4ad7e6\",\"formUrl\":\"https://forms.justmsme.com/registerinmsme/form/GSTFILING/formperma/s_kaTURw9fSEdVqNMbQslJXt5ER8nLQk0T-0w2zUUpE?zf_rszfm=1\"}]"
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