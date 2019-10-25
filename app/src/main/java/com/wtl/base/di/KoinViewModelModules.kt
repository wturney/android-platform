package com.wtl.base.di

import com.wtl.base.ui.resume.ResumeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {

    viewModel { ResumeViewModel(get()) }

}
