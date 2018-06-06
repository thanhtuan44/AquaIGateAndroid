package vn.aquavietnam.aquaiget.rxjava

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by ThanhTuan on 3/27/2018.
 */
class RxBus<T> {
    private object Holder {
        val INSTANCE = RxBus<Any>()
    }

    companion object {
        val instance: RxBus<Any> by lazy { Holder.INSTANCE }
    }

    private var bus: PublishSubject<T>? = null

    fun events(): PublishSubject<T>? {
        if (bus == null) {
            bus = PublishSubject.create<T>()
        }
        return bus
    }

    fun <T> events(eventClass: Class<T>): Observable<T> {
        return events()!!.ofType(eventClass)
    }

    fun send(o: T) {
        bus!!.onNext(o)
    }


    fun hasObservers(): Boolean {
        return bus!!.hasObservers()
    }

    fun observableWithEvent(eventName: String): Observable<DataBus<*>> {
        return events(DataBus::class.java).filter { o -> !o.eventName.isEmpty() && o.eventName.compareTo(eventName) === 0 }
    }
}