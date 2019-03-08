using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace Event.Tracker.RNEventTracker
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNEventTrackerModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNEventTrackerModule"/>.
        /// </summary>
        internal RNEventTrackerModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNEventTracker";
            }
        }
    }
}
