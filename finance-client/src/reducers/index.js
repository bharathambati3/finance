import { combineReducers } from "redux";
import ManageMonthlySipReducer from './ManageMonthlySipReducer'
import CommonReducer from './CommonReducer'
import DashboardReducer from './DashboardReducer'

const rootReducer = combineReducers({
  monthlySipData: ManageMonthlySipReducer,
  common: CommonReducer,
  dashboard: DashboardReducer,
});

export default rootReducer;