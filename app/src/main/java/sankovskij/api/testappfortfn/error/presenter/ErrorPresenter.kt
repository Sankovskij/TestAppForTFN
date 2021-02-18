package sankovskij.api.testappfortfn.error.presenter

import moxy.InjectViewState
import moxy.MvpPresenter
import sankovskij.api.testappfortfn.error.view.ErrorView


@InjectViewState
    class ErrorPresenter() : MvpPresenter<ErrorView>() {

        override fun onFirstViewAttach() {
            super.onFirstViewAttach()
            viewState.init()
        }


}