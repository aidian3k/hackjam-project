import { configureStore } from "@reduxjs/toolkit";
import languageReducer from "./features/language/language-slice";

export const globalStore = configureStore({
  reducer: {
    language: languageReducer,
  },
});

export type RootState = ReturnType<typeof globalStore.getState>;
export type AppDispatch = typeof globalStore.dispatch;
