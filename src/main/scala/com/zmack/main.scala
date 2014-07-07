package com.zmack
import scala.util.control.Breaks._
import dispatch._
import dispatch.Defaults._
import rx.lang.scala._
import scala.concurrent.ExecutionContext.global

object Main extends App {

  override def main(args: Array[String]):Unit = {
    println("Hello ")
    myObservable.flatMap(n => {
      val svc = url(s"http://localhost/foo?$n")
      Observable.from(Http(svc OK as.String))
    }).flatMap(req => {
      Thread.sleep(500L)
      Observable.items(req.split(" "))
    }).subscribe(s => {
      println(s)
    })
  }

  def myObservable = Observable[Integer](subscriber => {
    try {
      var i = 0

      while(true) {
        if (subscriber.isUnsubscribed) {
          break
        } else {
          subscriber.onNext(i)
          i = i + 1
        }
      }
    } catch {
      case e:Throwable => if (!subscriber.isUnsubscribed) subscriber.onError(e)
    }
  })

}

