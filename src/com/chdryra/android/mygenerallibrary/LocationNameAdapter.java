/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.chdryra.android.remoteapifetchers.FetcherPlacesAPI;
import com.google.android.gms.maps.model.LatLng;

public class LocationNameAdapter extends ArrayAdapter<String> implements Filterable {
	private static final String TAG = "LocationNameAdapter";
	private static final int RADIUS = 250;
	private static final String SEARCHING = "searching nearby...";
	private static final String NO_LOCATION = "location not found...";
	
    private ArrayList<String> mLocationSuggestions = null;
	private ArrayList<String> mLocationDefaultSuggestions = null;
	private String mPrimaryDefaultSuggestion;
	private LatLng mLatLng;
	
	public LocationNameAdapter(Context context, int textViewResourceId, LatLng latlng, int numberDefaultSuggestions, String primaryDefaultSuggestion) {
		super(context, textViewResourceId);
		mLatLng = latlng;
		if(numberDefaultSuggestions > 0) {
			if(primaryDefaultSuggestion != null && primaryDefaultSuggestion.length() > 0) {
				mPrimaryDefaultSuggestion = primaryDefaultSuggestion;
				numberDefaultSuggestions--;
			}
			
			mLocationSuggestions = new ArrayList<String>();
			if(mLatLng != null) {
				mLocationSuggestions.add(SEARCHING);
				
				GetAddressTask task = new GetAddressTask(context, mLatLng);
				task.execute(numberDefaultSuggestions);
			} else 
				mLocationSuggestions.add(NO_LOCATION);
			
			notifyDataSetChanged();
		}		
	}
	
	@Override
    public int getCount() {
		if(mLocationSuggestions != null)
			return mLocationSuggestions.size();
		else
			return 0;
    }

    @Override
    public String getItem(int index) {
        return mLocationSuggestions.get(index);
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
        		
        	@Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null && constraint.length() > 0) {
                	ArrayList<String> shortened = new ArrayList<String>();
                	ArrayList<String> suggestions = FetcherPlacesAPI.fetchAutoCompleteSuggestions(constraint.toString(), mLatLng, RADIUS);
                	for(String suggestion : suggestions)
                		shortened.add(formatAddress(suggestion));
                	mLocationSuggestions = shortened;
                }
                else if(mLocationDefaultSuggestions != null)
                    mLocationSuggestions = mLocationDefaultSuggestions;
           
                filterResults.values = mLocationSuggestions;
                filterResults.count = mLocationSuggestions.size();
                
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0)
                    notifyDataSetChanged();
                else
                    notifyDataSetInvalidated();
            }};
            
        return filter;
    }

    public void findSuggestions(String query) {
    	getFilter().filter(query);
    }
    
    public void findSuggestions(CharSequence query) {
    	getFilter().filter(query);
    }
    
    private String formatAddress(String address) {
		  String[] addressComponents = address.split(",");
		  StringBuilder sb = new StringBuilder();
		  sb.append(addressComponents[0]);
		  if(addressComponents.length > 1) {
			  sb.append(",");
			  sb.append(addressComponents[1]);
		  }
		  
		  return sb.toString();
	 }
	 
    private String formatAddress(Address address) {
		  String addressText = String.format(
	              "%s%s%s",
	              // If there's a street address, add it
	              address.getMaxAddressLineIndex() > 0 ?
	                      address.getAddressLine(0) : "",
	              // Locality is usually a city
	              address.getLocality() != null ?
	            		  ", " + address.getLocality(): "");

		  return addressText;
	 }
	    
    private class GetAddressTask extends AsyncTask<Integer, Void, ArrayList<String>> {
		  
		  Context mContext;
		  LatLng mLatLng;
		  
		  public GetAddressTask(Context context, LatLng latlng) {
			  super();
			  mContext = context;
			  mLatLng = latlng;
		  }
  
		  @Override
		  protected ArrayList<String> doInBackground(Integer... params) {
			  Integer numberToGet = params[0];
			  
			  ArrayList<String> namesFromGoogle = FetcherPlacesAPI.fetchNearestNames(mLatLng, numberToGet);
			  
			  if(namesFromGoogle.size() > 0)
				  return namesFromGoogle;
			  else {
				  //Try using Geocoder
				  Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
			
				  List<Address> addresses = null;
				  try {
				      addresses = geocoder.getFromLocation(mLatLng.latitude, mLatLng.longitude, numberToGet);
				  } catch (IOException e1) {
					  Log.e(TAG, "IO Exception trying to get address");
					  e1.printStackTrace();
				  } catch (IllegalArgumentException e2) {
					  Log.e(TAG, "Illegal Argument Exception trying to get address");
					  e2.printStackTrace();
				  }
				  
				  if(addresses != null && addresses.size() > 0) {
					  ArrayList<String> addressesList = new ArrayList<String>();
					  for(int i = 0; i<addressesList.size(); ++i)
						  addressesList.add(formatAddress(addresses.get(i)));
					  return addressesList;
				  }				  
				  else
					  return null;
				  }
		  }
	  
	@Override
	protected void onPostExecute(ArrayList<String> addresses) {
		super.onPostExecute(addresses);
		if(addresses != null) {
			mLocationDefaultSuggestions = addresses;
			if(mPrimaryDefaultSuggestion != null)
				mLocationDefaultSuggestions.add(0, mPrimaryDefaultSuggestion);
			mLocationSuggestions = new ArrayList<String>(mLocationDefaultSuggestions);
			notifyDataSetChanged();
		}
	}
}
}