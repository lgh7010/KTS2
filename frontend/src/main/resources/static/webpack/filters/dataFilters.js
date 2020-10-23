import Vue from 'vue'
import Moment from 'moment'

Vue.filter('utcToLocaltime', function (dateStr) {
    if (dateStr === undefined || dateStr === null || dateStr === '') return ''
    dateStr = dateStr.toString()
    let localTime = Moment.utc(dateStr).toDate()
    return Moment(localTime).format('YYYY-MM-DD HH:mm:ss')
})

Vue.filter('utcToLocaltimeAddSeconds', function (dateStr, seconds) {
    if (dateStr === undefined || dateStr === null || dateStr === '') return ''
    if (seconds === undefined || seconds === null || seconds === '') return ''
    dateStr = dateStr.toString()
    let localTime = Moment.utc(dateStr).toDate()
    return Moment(localTime).add(seconds, 'second').format('YYYY-MM-DD HH:mm:ss')
})

export default 'dataFilters'
