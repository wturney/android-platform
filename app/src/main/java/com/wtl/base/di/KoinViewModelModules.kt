package com.wtl.base.di

import com.wtl.base.api.model.Employer
import com.wtl.base.ui.employer.EmployerViewModel
import com.wtl.base.ui.resume.ResumeViewModel
import com.wtl.base.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {

    viewModel { SplashViewModel() }
    viewModel { (employer: Employer) -> EmployerViewModel(employer) }
    viewModel { ResumeViewModel(get()) }

}
